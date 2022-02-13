import request from "@/utils/request";

export function fetchAppInfos(){
  return request({
    url: '/appInfo/',
    method: "get",
  });
}

export function fetchAppInfo(query) {
  return request({
    url: "/instanceInfo/" + query,
    method: "get",
  });
}
