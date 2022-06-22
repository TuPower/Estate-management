<%--
  Created by IntelliJ IDEA.
  User: congt
  Date: 3/18/2022
  Time: 8:43 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="loadStaffAPI" value="/api/building"></c:url>
<c:url var="deleteBuildingAPI" value="/api/building/delete"></c:url>
<c:url var="buildingListURL" value="/admin/building-list"></c:url>
<c:url var="buildingEditURL" value="/admin/building-edit"></c:url>
<c:url var="buildingUpdateURL" value="/admin/building-update-"></c:url>
<c:url var="assignBuilding" value="/api/building/assignBuilding"></c:url>
<html>
<head>
    <title>Danh sách toàn nhà</title>
</head>
<body>
<div class="main-content">
    <div class="main-content-inner">
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
                Danh sách tòa nhà
            </li>
        </ul>
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
                        <div class="col-md-12">
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
                                        <div class="form-horizontal">
                                            <div class="form-group">
                                                <form:form commandName="modelSearch" action="${buildingListURL}" id="listForm" method="GET">
                                                    <div class="row container">
                                                        <div class="row container">
                                                            <div class="col-md-6">
                                                                Tên sản phẩm
                                                                <form:input path="name" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-6">
                                                                Diện tích sàn
                                                                <form:input path="floorArea" cssClass="form-control"></form:input>
                                                            </div>
                                                        </div>

                                                        <div class="row container" >
                                                            <div class="col-md-4">
                                                                Quận hiện có
                                                                <form:select path="district">
                                                                    <form:option value="">Chọn quận</form:option>
                                                                    <form:options items="${districts}"></form:options>
                                                                </form:select>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <p>Phường</p>
                                                                <form:input path="ward" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <p>Đường</p>
                                                                <form:input path="street" cssClass="form-control"></form:input>
                                                            </div>
                                                        </div>


                                                        <div class="row container" >
                                                            <div class="col-md-4">
                                                                <p>Số tầng hầm</p>
                                                                <form:input path="numberOfBasement" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <p>Hướng</p>
                                                                <form:input path="direction" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <p>Hạng</p>
                                                                <form:input path="level" cssClass="form-control"></form:input>
                                                            </div>
                                                        </div>

                                                        <div class="row container" >
                                                            <div class="col-md-3">
                                                                <p>Diện tích từ</p>
                                                                <form:input path="areaFrom" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <p>Diện tích đến</p>
                                                                <form:input path="areaTo" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <p>Giá thuê từ</p>
                                                                <form:input path="rentFrom" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-3">
                                                                <p>Giá thuê đến</p>
                                                                <form:input path="rentTo" cssClass="form-control"></form:input>
                                                            </div>
                                                        </div>

                                                        <div class="row container" >
                                                            <div class="col-md-4">
                                                                <p>Tên quản lý</p>
                                                                <form:input path="managerName" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <p>Điện thoại quản lý</p>
                                                                <form:input path="managerPhone" cssClass="form-control"></form:input>
                                                            </div>
                                                            <div class="col-md-4">
                                                                <p>Chọn nhân viên phụ trách</p>
                                                                <form:select path="staffID">
                                                                    <form:option value="">Chọn nhân viên phụ trách</form:option>
                                                                    <form:options items="${staffMaps}"></form:options>
                                                                </form:select>
                                                            </div>
                                                        </div>

                                                        <div class="row container ">
                                                            <form:checkboxes path="types" items="${buildingTypes}"></form:checkboxes>
                                                        </div>
                                                        <div class="pt-3">
                                                            <button type="button" class="btn btn-outline-warning" onclick="searchBuiding()" >Tìm kiếm</button>
                                                        </div>
                                                    </div>
                                                </form:form>
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
                                           title="Thêm sản phẩm"
                                           href='<c:url value="${buildingEditURL}"/>'>
                                                <span>
                                                    <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                </span>
                                        </a>
                                        <button id="btnDelete" type="button"
                                                class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                data-toggle="tooltip"
                                                title="Xóa sản phẩm">
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
                                <table class="table table-striped text-center" id="buildingList">
                                    <thead>
                                        <tr>
                                            <th></th>
                                            <th>Tên sản phẩm</th>
                                            <th>Số tầng hầm</th>
                                            <th>Địa chỉ</th>
                                            <th>Tên quản lý</th>
                                            <th>Số điện thoại</th>
                                            <th>Diện tích sàn</th>
                                            <th>Giá thuê</th>
                                            <th>Phí dịch vụ</th>
                                            <th>Thao tác</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                    <c:forEach var="item" items="${buildings}">
                                        <tr>
                                            <td><input type="checkbox" id="id" name="id" value='${item.id}'></td>
                                            <td>${item.name}</td>
                                            <td>${item.numberOfBasement}</td>
                                            <td>${item.district}</td>
                                            <td>${item.managerName}</td>
                                            <td>${item.managerPhone}</td>
                                            <td>${item.floorArea}</td>
                                            <td>${item.rentPrice}</td>
                                            <td>${item.serviceFee}</td>
                                            <td >
                                                <button type="button" class="btn btn-default icon" data-bs-toggle="tooltip"
                                                        title="Giao tòa nhà" onclick="assigmentBuilding(${item.id})">
                                                    <i class="bi bi-list-check" aria-hidden="true"></i>
                                                </button>
                                                <button type="button" class="btn btn-default icon" data-bs-toggle="tooltip"
                                                        title="Sửa tòa nhà"
                                                        onclick="updateBuilding(${item.id})">
                                                    <i class="bi bi-list-check" aria-hidden="true"></i>
                                                </button>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
                                <!--<input type="hidden" id="id" name="id" value="">-->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
    <div class="modal fade" id="assigmentBuildingMadel">
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
                    <button type="button" class="btn btn-default" data-dismiss="modal" id="btnAssignBuilding" >Giao tòa nhà</button>
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </div>

            </div>
        </div>
    </div>
    <!-- End Modal -->
    <script>
        function assigmentBuilding(id){
            openModalassigmentBuilding();
            loadStaff(id);
            $('#id').val(id);
        };
        function updateBuilding(id){
            $(location).attr('href', '${buildingUpdateURL}'+id+'');
        };
        function openModalassigmentBuilding() {
            $('#assigmentBuildingMadel').modal('show');
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
        }
        function searchBuiding() {
            $("#listForm").submit();
        };

       $('#btnAssignBuilding').click(function (e) {
           e.preventDefault();
           var data = {};
           data['id'] = $('#id').val();

           var staffs = $('#staffList').find('tbody input:checked').map(function () {
               return $(this).val();
           }).get();
           data['staffs'] = staffs;
           assignStaff(data);
       });

        $('#btnDelete').click(function (e) {
            e.preventDefault();
            var data = {};
            var id = $('#buildingList').find('tbody input:checked').map(function () {
                return $(this).val();
            }).get();
            data['ids'] = id;
            delelteBuilding(data);
        });

        function delelteBuilding(data) {
            $.ajax({
                type: 'DELETE',
                url: '${deleteBuildingAPI}',
                data: JSON.stringify(data),
                //dataType: "json",
                contentType:"application/json" ,
                success: function (response) {
                    $(location).attr("href", "${buildingListURL}");
                    console.log(response);
                },
                error: function (response) {
                    alert("failed");
                }
            });
        }

       function assignStaff(data) {
           $.ajax({
               type: 'POST',
               url: '${assignBuilding}',
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
