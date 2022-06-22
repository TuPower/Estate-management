<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="customerListURL" value="/admin/customer-list"></c:url>
<c:url var="loadStaffAPI" value="/api/customer"></c:url>
<c:url var="assignCustomer" value="/api/customer/assignCustomer"></c:url>
<c:url var="customerUpdateURL" value="/admin/customer-update-"></c:url>
<c:url var="customerDeleteAPI" value="/api/customer/delete"></c:url>
<%--
  Created by IntelliJ IDEA.
  User: congt
  Date: 6/8/2022
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Danh sách khách hàng</title>
</head>
<body>
<div class="main-content">
    <form:form action="${customerListURL}" id="listForm" method="GET" commandName="customerModel">
        <div class="main-content-inner">
            <div class="breadcrumbs" id="breadcrumbs">
                <script type="text/javascript">
                    try {
                        ace.settings.check('breadcrumbs', 'fixed')
                    } catch (e) {
                    }
                </script>

                <ul class="breadcrumb">
                    <li>
                        <i class="ace-icon fa fa-home home-icon"></i>
                        <a href="<c:url value="/admin/home"/>">
                                <%--<spring:message code="label.home"/>--%>
                            Trang chủ
                        </a>
                    </li>
                    <li class="active">
                            <%--<spring:message code="label.user.list"/>--%>
                        Danh sách khách hàng
                    </li>
                </ul>
                <!-- /.breadcrumb -->
            </div>
            <div class="page-content">
                <div class="row">
                    <div class="col-xs-12">
                        <c:if test="${messageResponse!=null}">
                            <div class="alert alert-block alert-${alert}">
                                <button type="button" class="close" data-dismiss="alert">
                                    <i class="ace-icon fa fa-times"></i>
                                </button>
                                    ${messageResponse}
                            </div>
                        </c:if>
                        <!-- PAGE CONTENT BEGINS -->
                        <div class="row">
                            <div class="col-xs-12">
                                <div class="widget-box table-filter">
                                    <div class="widget-header">
                                        <h4 class="widget-title">
                                                <%--<spring:message code="label.search"/>--%>
                                            Tìm kiếm
                                        </h4>
                                        <div class="widget-toolbar">
                                            <a href="#" data-action="collapse">
                                                <i class="ace-icon fa fa-chevron-up"></i>
                                            </a>
                                        </div>
                                    </div>
                                    <div class="widget-body">
                                        <div class="widget-main">
                                                <div class="row container">
                                                    <div>
                                                        <div class="col-md-4">
                                                            Tên khách hàng
                                                            <form:input path="name"  cssClass="form-control"></form:input>
                                                        </div>
                                                        <div class="col-md-4">
                                                            Di động
                                                            <form:input path="phone" cssClass="form-control"></form:input>
                                                        </div>
                                                        <div class="col-md-4">
                                                            Email
                                                            <form:input path="email" cssClass="form-control"></form:input>
                                                        </div>
                                                    </div>
                                                    <div>
                                                        <div class="col-md-4">
                                                            <p>Chọn nhân viên phụ trách</p>
                                                            <form:select path="staffId">
                                                                <form:option value="">Chọn nhân viên phụ trách</form:option>
                                                                <form:options items="${staffMaps}"></form:options>
                                                            </form:select>
                                                        </div>
                                                    </div>
                                                    <div class="form-group">
                                                        <label class="col-sm-2 control-label"></label>
                                                        <div class="col-sm-8">
                                                            <button id="btnSearch" onclick="searchCustomer()" type="button"
                                                                    class="btn btn-sm btn-success">
                                                                    <%--spring:message code="label.search"/>--%>
                                                                Tìm kiếm
                                                                <i class="ace-icon fa fa-arrow-right icon-on-right bigger-110"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="table-btn-controls">
                                    <div class="pull-right tableTools-container">
                                        <div class="dt-buttons btn-overlap btn-group">
                                            <a flag="info"
                                               class="dt-button buttons-colvis btn btn-white btn-primary btn-bold"
                                               data-toggle="tooltip"
                                                <%--title='<spring:message code="label.user.add"/>'--%>
                                               title="Thêm người dùng"
                                               href='<c:url value="/admin/customer-edit"/>'>
															<span>
																<i class="fa fa-plus-circle bigger-110 purple"></i>
															</span>
                                            </a>
                                            <button id="btnDelete" type="button"
                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                    data-toggle="tooltip"
                                                    title="Xóa khách hàng">
                                                    <span>
                                                        <i class="fa fa-trash-o bigger-110 pink"></i>
                                                    </span>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div>
                            <div class="row">
                                <div class="card">
                                    <div class="card-header">
                                        <h5 class="card-title">Danh sách sản phẩm</h5>
                                    </div>
                                    <table class="table table-striped" id="customerList">
                                        <thead class="text-center">
                                        <tr>
                                            <th></th>
                                            <th>Tên</th>
                                            <th>Di động</th>
                                            <th>Email</th>
                                            <th>Nhu cầu</th>
                                            <th>Người nhập</th>
                                            <th>Ngày nhập</th>
                                            <th>Tình trạng</th>
                                            <th>Thao tác</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <c:forEach var="item" items="${customers}">
                                            <tr>
                                                <td><input type="checkbox" id="id" name="id" value='${item.id}'></td>
                                                <td>${item.name}</td>
                                                <td>${item.phone}</td>
                                                <td>${item.email}</td>
                                                <td>${item.needs}</td>
                                                <td>${item.createdBy}</td>
                                                <td>${item.createdDate}</td>
                                                <td>${item.status}</td>
                                                <td >
                                                    <button type="button" class="btn btn-default icon" data-bs-toggle="tooltip"
                                                            title="Giao khách hàng cho nhân viên" onclick="assigmentCustomer(${item.id})">
                                                        <i class="bi bi-list-check" aria-hidden="true"></i>
                                                    </button>
                                                    <button type="button" class="btn btn-default icon" data-bs-toggle="tooltip"
                                                            title="Cập nhập khách hàng"
                                                            onclick="updateCustomer(${item.id})">
                                                        <i class="bi bi-list-check" aria-hidden="true"></i>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </form:form>
</div>
<div class="modal fade" id="assigmentCustomerMadel">
    <div class="modal-dialog">
        <div class="modal-content">

            <!-- Modal Header -->
            <div class="modal-header">
                <h4 class="modal-title">Danh sách nhân viên</h4>
            </div>
            <!-- Modal body -->
            <div class="modal-body">
                <div class="card-body">
                    <!-- Active Table -->
                    <table class="table table-borderless" id="staffList" >
                        <thead>
                        <tr>
                            <th class="text-center">
                                Chọn nhân viên
                            </th>
                            <th class="text-center">
                                Tên nhân viên
                            </th>
                        </tr>
                        </thead>
                        <tbody>
                        </tbody>
                    </table>
                    <!-- End Tables without borders -->
                </div>
            </div>
            <!-- Modal footer -->
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="btnAssignCustomer" >Giao tòa nhà</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>

        </div>
    </div>
</div>
<!-- End Modal -->
<script>
    $('#btnDelete').click(function (e) {
        e.preventDefault();
        var data = {};
        var id = $('#customerList').find('tbody input:checked').map(function () {
            return $(this).val();
        }).get();
        data['ids'] = id;
        delelteCustomer(data);
    });
    function delelteCustomer(data) {
        $.ajax({
            type: 'DELETE',
            url: '${customerDeleteAPI}',
            data: JSON.stringify(data),
            //dataType: "json",
            contentType:"application/json" ,
            success: function (response) {
                $(location).attr("href", "${customerListURL}");
                console.log(response);
            },
            error: function (response) {
                alert("failed");
            }
        });
    }
    function updateCustomer(id){
        $(location).attr('href', '${customerUpdateURL}'+id+'');
    };
    function searchCustomer() {
        $("#listForm").submit();
    };
    function assigmentCustomer(id){
        openModalassigmentCustomer();
        loadStaff(id);
        $('#id').val(id);
    };
    function openModalassigmentCustomer() {
        $('#assigmentCustomerMadel').modal('show');
    };
    function loadStaff(id) {
        $.ajax({
            type: 'GET',
            url: '${loadStaffAPI}/'+id+'/staffs',
            //data: JSON.stringify(buildingid),
            dataType: "json",
            //contentType:"application/json" ,
            success: function (response) {
                var row='';
                $.each(response,function (index,item) {
                    row += '<tr>';
                    row += '<td class="text-center"> <input type="checkbox" value='+item.id+' id="checkbox_'+item.id+'" class="check-box-element" '+item.checked+'></td>';
                    row += '<td class="text-center">'+item.name+'</td>';
                    row += '</tr>';
                });
                $('#staffList tbody').html(row);
            },
            error: function (response) {
                console.log('failed');
                console.log(response);
            }
        });
    };
    $('#btnAssignCustomer').click(function (e) {
        e.preventDefault();
        var data = {};
        data['id'] = $('#id').val();

        var staffs = $('#staffList').find('tbody input:checked').map(function () {
            return $(this).val();
        }).get();
        data['staffs'] = staffs;
        assignStaff(data);
    });
    function assignStaff(data) {
        $.ajax({
            type: 'POST',
            url: '${assignCustomer}',
            data: JSON.stringify(data),
            //dataType: "json",
            contentType:"application/json" ,
            success: function (response) {
                console.log(response);
            },
            error: function (response) {
                alert("failed");
            }
        });
    }

</script>
</body>
</html>
