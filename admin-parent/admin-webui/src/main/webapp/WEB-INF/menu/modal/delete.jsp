<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/9
  Time: 14:33
  To change this template use File | Settings | File Templates.
  menu菜单删除模态框
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menuConfirmModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Crowd菜单删除</h4>
            </div>
            <form>
                <div class="modal-body">
                    确定删除节点<span id="removeNodeSpan"></span>吗？
                </div>
                <div class="modal-footer">
                    <button id="confirmBtn" type="button" class="btn btn-danger"><i class="glyphicon glyphicon-ok"></i>
                        确定
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
