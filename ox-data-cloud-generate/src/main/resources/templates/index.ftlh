<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Butterfly代码生成</title>
    <script src="https://cdn.jsdelivr.net/npm/vue@2.6.12"></script>
    <!-- 引入样式 -->
    <link rel="stylesheet" href="https://unpkg.com/element-ui/lib/theme-chalk/index.css">
    <!-- 引入组件库 -->
    <script src="https://unpkg.com/element-ui/lib/index.js"></script>
    <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
<div id="app">
    <el-container>
        <el-header style="background-color: #409EFF">
            <div style="line-height: 60px;font-weight: bold;font-size:22px;color: white">
                Butterfly代码生成
            </div>
        </el-header>
        <el-main>
            <div style="margin-top: 20px;">
                <el-row>
                    <el-form :inline="true" v-model="queryForm">
                        <el-form-item size="mini" label="名称">
                            <el-input v-model="queryForm.tableName" clearable></el-input>
                        </el-form-item>
                        <el-form-item size="mini" label="备注">
                            <el-input v-model="queryForm.comment" clearable></el-input>
                        </el-form-item>
                        <el-form-item size="mini">
                            <el-button type="primary" @click="pageQueryTable">查询</el-button>
                        </el-form-item>
                        <el-form-item size="mini">
                            <el-button type="primary" @click="showGenerateCodeConfig"
                                       :disabled="multipleSelection.length ===0">生成代码
                            </el-button>
                        </el-form-item>
                    </el-form>
                </el-row>
                <el-row>
                    <el-table :data="tableList" size="mini"
                              @selection-change="handleSelectionChange">
                        <el-table-column
                                type="selection">
                        </el-table-column>
                        <el-table-column
                                prop="tableName"
                                label="表名称">
                        </el-table-column>
                        <el-table-column
                                prop="comment"
                                label="备注信息">
                        </el-table-column>
                        <el-table-column
                                prop="engine"
                                label="存储引擎">
                        </el-table-column>
                        <el-table-column
                                prop="createTime"
                                label="更时间">
                        </el-table-column>
                    </el-table>
                    <el-pagination
                            style="float: right"
                            @size-change="handleSizeChange"
                            @current-change="handleCurrentChange"
                            :current-page.sync="queryForm.page"
                            :page-size="queryForm.size"
                            layout="total, prev, pager, next"
                            :total="total">
                    </el-pagination>
                </el-row>
                <el-dialog
                        title="系统配置"
                        :visible.sync="systemConfigDialogVisible"
                        width="50%">
                    <div>
                        <el-form :model="configForm" size="mini" label-width="110px">
                            <el-form-item label="作者">
                                <el-input v-model="configForm.author" placeholder="请输入作者"></el-input>
                            </el-form-item>
                            <el-form-item label="包名">
                                <el-input v-model="configForm.packageName" placeholder="请输入基础包名"></el-input>
                            </el-form-item>
                            <el-form-item label="继承BaseEntity">
                                <el-radio v-model="configForm.extendsBaseEntity" :label="true">是</el-radio>
                                <el-radio v-model="configForm.extendsBaseEntity" :label="false">否</el-radio>
                            </el-form-item>
                        </el-form>
                    </div>
                    <span slot="footer" class="dialog-footer">
                        <el-button @click="systemConfigDialogVisible = false" size="mini">取 消</el-button>
                        <el-button type="primary" @click="generateCode" size="mini">确 定</el-button>
                    </span>
                </el-dialog>
            </div>
        </el-main>
    </el-container>
</div>
</body>
<script>
    const app = new Vue({
        el: '#app',
        data: {
            message: 'hello',
            total: 0,
            queryForm: {
                tableName: '',
                comment: '',
                page: 1,
                size: 20
            },
            tableList: [],
            multipleSelection: [],
            systemConfigDialogVisible: false,
            configForm: {
                author: 'wangxian',
                packageName: 'org.butterfly',
                extendsBaseEntity: true,
            },
            generateCodeForm: {
                config: {},
                tables: [],
            }
        },
        created: function () {
            this.pageQueryTable()
        },
        methods: {
            showGenerateCodeConfig() {
                const _this = this;
                _this.systemConfigDialogVisible = true
            },
            handleSelectionChange(val) {
                console.log(val)
                this.multipleSelection = val.map(e => e.tableName);
            },
            generateCode() {
                const _this = this;
                this.$message.success('代码生成中')
                _this.generateCodeForm.config = _this.configForm;
                _this.generateCodeForm.tables = _this.multipleSelection;
                // axios.post('/generate/code/zip', _this.generateCodeForm);
                axios({
                    method: 'post',
                    url: '/generate/code/zip', // 请求地址
                    data: _this.generateCodeForm, // 参数
                    responseType: 'blob' // 表明返回服务器返回的数据类型
                }).then((res) => { // 处理返回的文件流
                    const blob = new Blob([res.data], {type: "application/octet-stream"})
                    console.log('blog', res.data)
                    const fileName = 'code.zip'
                    if ('download' in document.createElement('a')) { // 非IE下载
                        const elink = document.createElement('a')
                        elink.download = fileName
                        elink.style.display = 'none'
                        elink.href = URL.createObjectURL(blob)
                        document.body.appendChild(elink)
                        elink.click()
                        URL.revokeObjectURL(elink.href) // 释放URL 对象
                        document.body.removeChild(elink)
                    } else { // IE10+下载
                        navigator.msSaveBlob(blob, fileName)
                    }
                })
            },
            handleSizeChange(val) {
                this.queryForm.size = val
                this.pageQueryTable()
            },
            handleCurrentChange(val) {
                this.queryForm.page = val
                this.pageQueryTable()
            },
            pageQueryTable() {
                const _this = this;
                axios.get('/generate/tables/page', {params: this.queryForm})
                    .then(function (response) {
                        const data = response.data.data
                        _this.tableList = data.list
                        _this.total = parseInt(data.total)
                    })
                    .catch(function (error) {
                        console.log(error);
                    });
            }
        }
    });
</script>
<style>
    * {
        margin: 0;
        padding: 0;
    }
</style>
</html>
