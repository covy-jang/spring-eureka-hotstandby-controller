package org.flaton.reducontrol.collect;

import com.google.common.collect.Maps;
import com.netflix.appinfo.InstanceInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.flaton.reducontrol.domain.dto.InstanceInfoDto;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Slf4j
@RequiredArgsConstructor
@Component
public class InstanceStatusContextSwitcher {
    private final EurekaContextRegistry eurekaContextRegistry;
    private Map<String, InstanceInfoDto> instanceInfoSnapShot = Maps.newConcurrentMap();
    private Optional<Consumer<InstanceInfoDto>> listener = Optional.empty();

    public void registerEventListener(Consumer<InstanceInfoDto> consumer) {
        this.listener = Optional.of(consumer);
    }

    public void subscribe(InstanceInfoDto instanceInfoDto) {
        InstanceWatchEvent event = watchEvent(instanceInfoDto);
        if (event != InstanceWatchEvent.SAME) {
            instanceInfoSnapShot.put(instanceInfoDto.getInstanceId(), instanceInfoDto);
            notify(instanceInfoDto);
        }
    }

    public void notify(InstanceInfoDto instanceInfoDto) {
        this.listener.ifPresent(consumer -> consumer.accept(instanceInfoDto));
    }

    private InstanceWatchEvent watchEvent(InstanceInfoDto instanceInfoDto) {
        if (!instanceInfoSnapShot.containsKey(instanceInfoDto.getInstanceId()))
            return InstanceWatchEvent.START;
        InstanceInfoDto snapshot = instanceInfoSnapShot.get(instanceInfoDto.getInstanceId());
        if (snapshot.equals(instanceInfoDto))
            return InstanceWatchEvent.SAME;
        else if (snapshot.getStatus().equals(instanceInfoDto.getStatus()))
            return InstanceWatchEvent.SYNC;
        else if (instanceInfoDto.getStatus() == InstanceInfo.InstanceStatus.STARTING)
            return InstanceWatchEvent.START;
        else if (instanceInfoDto.getStatus() == InstanceInfo.InstanceStatus.UP)
            return InstanceWatchEvent.UP;
        else if (instanceInfoDto.getStatus() == InstanceInfo.InstanceStatus.DOWN)
            return InstanceWatchEvent.DOWN;
        else
            return InstanceWatchEvent.QUIT;
    }

    public boolean containInstanceInfo(InstanceInfoDto instanceInfoDto) {
        if(!instanceInfoSnapShot.containsKey(instanceInfoDto.getInstanceId()))
            return false;

        if(!eurekaContextRegistry.contains(instanceInfoDto)){
            log.info("Flush Dummy Instance Info : {}", instanceInfoDto);
            instanceInfoSnapShot.remove(instanceInfoDto);
            return false;
        }

        return true;
    }
}