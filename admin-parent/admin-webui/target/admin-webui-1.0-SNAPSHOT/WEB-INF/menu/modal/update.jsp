<%--
  Created by IntelliJ IDEA.
  User: nichijoux
  Date: 2022/8/9
  Time: 14:31
  To change this template use File | Settings | File Templates.
  menu菜单更新模态框
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div id="menuEditModal" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Crowd菜单更新</h4>
            </div>
            <form>
                <div class="modal-body">
                    <label>
                        请输入节点名称：
                        <input type="text" name="name"/>
                    </label><br/>
                    <label>
                        请输入URL地址：
                        <input type="text" name="url"/>
                    </label><br/>
                    <i class="glyphicon glyphicon-th-list"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-th-list"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-dashboard"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-dashboard"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon glyphicon-tasks"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon glyphicon-tasks"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-user"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-user"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-king"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-king"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-lock"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-lock"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-ok"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-ok"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-check"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-check"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-th-large"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-th-large"/>

                    </label> <br/>

                    <i class="glyphicon glyphicon-picture"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-picture"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-equalizer"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-equalizer"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-random"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-random"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-hdd"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-hdd"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-comment"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-comment"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-list"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-list"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-tags"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-tags"/>
                        &nbsp;
                    </label>

                    <i class="glyphicon glyphicon-list-alt"></i>
                    <label>
                        <input type="radio" name="icon" value="glyphicon glyphicon-list-alt"/>
                        &nbsp;
                    </label>
                    <br/>

                </div>
                <div class="modal-footer">
                    <button id="menuEditBtn" type="button" class="btn btn-default"><i
                            class="glyphicon glyphicon-edit"></i> 更新
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>
