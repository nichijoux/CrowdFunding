<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/9
  Time: 9:59
  To change this template use File | Settings | File Templates.
  角色添加模态框
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="addModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">系统弹窗</h4>
            </div>
            <div class="modal-body">
                <form class="form-signin" role="form">
                    <div class="form-group has-success has-feedback">
                        <label>
                            <input type="text" name="roleName" class="form-control" placeholder="请输入角色名称" autofocus>
                        </label>
                        <span class="glyphicon glyphicon-user form-control-feedback"></span>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="saveRoleBtn" type="button" class="btn btn-primary">保存</button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->

