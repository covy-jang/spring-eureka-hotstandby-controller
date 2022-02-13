<template>
  <div class="app-container">
    <div class="filter-container">
      <el-select v-model="listQuery.appInfo" placeholder="AppInfos" clearable style="width: 200px" class="filter-item">
        <el-option v-for="item in appInfos" :key="item" :label="item" :value="item" />
      </el-select>
      <el-button v-waves class="filter-item" style="margin-left: 10px;"  type="primary" icon="el-icon-search" @click="handleFilter">
        Search
      </el-button>
    </div>
    <el-table
      :key="tableKey"
      v-loading="listLoading"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
    >
      <el-table-column label="appName" sortable="custom" align="center" width="180">
        <template slot-scope="{row}">
          <span>{{ row.appName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="instanceId" sortable="custom" align="center" width="300">
        <template slot-scope="{row}">
          <span class="link-type">{{ row.instanceId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="ipAddr" sortable="custom" align="center" width="150">
        <template slot-scope="{row}">
          <span>{{ row.ipAddr }}</span>
        </template>
      </el-table-column>
      <el-table-column label="sid" sortable="custom" align="center" width="150">
        <template slot-scope="{row}">
          <span>{{ row.sid }}</span>
        </template>
      </el-table-column>
      <el-table-column label="hostName" sortable="custom" align="center" width="200">
        <template slot-scope="{row}">
          <span>{{ row.hostName }}</span>
        </template>
      </el-table-column>
      <el-table-column label="status" class-name="status-col" sortable="custom" align="center" width="100">
        <template slot-scope="{row}">
          <el-tag :type="row.status | statusFilter">
            {{ row.status }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="redundancy" class-name="status-col" sortable="custom" align="center" width="150">
        <template slot-scope="{row}">
          <el-tag :type="row.redundancy | redundancyFilter">
            {{ row.redundancy }}
          </el-tag>
        </template>
      </el-table-column>
      <el-table-column label="lastUpdatedTimestamp" sortable="custom" align="center" width="210">
        <template slot-scope="{row}">
          <span>{{ row.lastUpdatedTimestamp }}</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
import { Pagination } from "@/components/Pagination"; // secondary package based on el-pagination
import waves from '@/directive/waves' // waves directive
import { fetchAppInfos, fetchAppInfo } from "@/api/appInfo";

export default {
  name: "Redundancy",
  components: { Pagination },
  directives: { waves },
  filters: {
    statusFilter(status) {
      const statusMap = {
        UP: 'success',
        DOWN: 'danger'
      }
      return statusMap[status]
    },
    redundancyFilter(redundancy){
      const redundancyMap = {
        ACTIVE: 'success',
        STANDBY: 'danger'
      }
      return redundancyMap[redundancy]
    }
  },
  data() {
    return {
      tableKey: 0,
      list: null,
      listLoading: false,
      listQuery: {
        appInfo: undefined,
        sort: "+id",
      },
      appInfos: []
    };
  },
  created() {
    this.getAppInfos();
  },
  methods: {
    getAppInfos(){
      this.listLoading = true;
      this.appInfos = [];
      fetchAppInfos().then((response) => {
        const data = response;
        for(let i = 0; i < data.length; ++i){
          this.appInfos.push(data[i].appName);
        }
        setTimeout(() => {
          this.listLoading = false;
        }, 1.5 * 1000);
      })
    },
    getAppInfo() {
      this.listLoading = true;
      this.list = [];
      fetchAppInfo(this.listQuery.appInfo).then((response) => {
        const data = response;
        for(let i = 0; i < data.length; ++i){
          this.list.push(data[i]);
        }
        // Just to simulate the time of the request
        setTimeout(() => {
          this.listLoading = false;
        }, 0.5 * 1000);
      });
    },
    handleFilter(){
      if(this.listLoading)
        return
      this.getAppInfo()
    }
  }
};
</script>
