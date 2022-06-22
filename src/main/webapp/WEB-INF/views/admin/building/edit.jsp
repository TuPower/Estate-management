
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="buildingAPI" value="/api/building"></c:url>
<c:url var="buildingEditURL" value="/admin/building-edit"></c:url>
<c:url var="buildingListURL" value="/admin/building-list"></c:url>
<html>
<head>
    <title>Thêm tòa nhà</title>
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
                        <div class="col-xs-12">
                            <div class="form-horizontal">
                                <div class="form-group">
                                    <form:form commandName="modelUpdate" class="form-horizontal" role="form" id="formData">
                                        <div class="row container">
                                            <div class="col-md-12" >
                                                <p>Tên sản phẩm</p>
                                                <input class="form-control" type="text" id="name" name="name" value="${modelUpdate.name}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Quận</p>
                                                <form:select path="district">
                                                    <form:option value="" >Chọn Quận</form:option>
                                                    <form:options items="${districts}"></form:options>
                                                </form:select>
                                            </div>
                                            <div class="col-md-12" >
                                                <p >Phường</p>
                                                <input class="form-control" type="text" id="ward" name="ward" value="${modelUpdate.ward}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p >Đường</p>
                                                <input class="form-control" type="text" id="street" name="street" value="${modelUpdate.street}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Kết cấu</p>
                                                <input class="form-control" type="text" id="structure" name="structure" value="${modelUpdate.structure}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p >Số tầng hầm</p>
                                                <input class="form-control" type="number" id="numberofbasement" name="numberofbasement" value="${modelUpdate.numberofbasement}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Diện tích sàn</p>
                                                <input class="form-control" type="number" id="floorarea" name="floorarea" value="${modelUpdate.floorarea}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Hướng</p>
                                                <input class="form-control" type="text" id="direction" name="direction" value="${modelUpdate.direction}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Hạng</p>
                                                <input class="form-control" type="text" id="level" name="level" value="${modelUpdate.level}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Diện tích thuê</p>
                                                <input class="form-control" type="text" id="rentarea" name="rentarea" value="${modelUpdate.rentarea}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Mô tả diện tích</p>
                                                <input class="form-control" type="text" id="areapricedescription" name="areapricedescription" value="${modelUpdate.areapricedescription}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Giá thuê</p>
                                                <input class="form-control" type="text" id="rentprice" name="rentprice" value="${modelUpdate.rentprice}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Mô tả giá</p>
                                                <input class="form-control" type="text" id="rentpricedescription" name="rentpricedescription" value="${modelUpdate.rentpricedescription}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Phí dịch vụ</p>
                                                <input class="form-control" type="text" id="servicefee" name="servicefee" value="${modelUpdate.servicefee}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Phí ô tô</p>
                                                <input class="form-control" type="text" id="carfee" name="carfee" value="${modelUpdate.carfee}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Phí mô tô</p>
                                                <input class="form-control" type="text" id="motobikefee" name="motobikefee" value="${modelUpdate.motobikefee}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Phí ngoài giờ</p>
                                                <input class="form-control" type="text" id="overtimefee" name="overtimefee" value="${modelUpdate.overtimefee}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Tiền điện</p>
                                                <input class="form-control" type="text" id="electricityfee" name="electricityfee" value="${modelUpdate.electricityfee}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Tiền cọc</p>
                                                <input class="form-control" type="text" id="deposit" name="deposit" value="${modelUpdate.deposit}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Thanh toán</p>
                                                <input class="form-control" type="text" id="payment" name="payment" value="${modelUpdate.payment}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Thời gian thuê</p>
                                                <input class="form-control" type="text" id="renttime" name="renttime" value="${modelUpdate.renttime}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Thời gian trang trí</p>
                                                <input class="form-control" type="text" id="decoreationtime" name="decoreationtime" value="${modelUpdate.decoreationtime}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Tên quan lý</p>
                                                <input class="form-control" type="text" id="managername" name="managername" value="${modelUpdate.managername}" ></input>
                                            </div>
                                            <div class="col-md-12">
                                                <p>SDT quản lý</p>
                                                <input class="form-control" type="text" id="managerphone" name="managerphone" value="${modelUpdate.managerphone}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Phí môi giới</p>
                                                <input class="form-control" type="text" id="brokeragefee" name="brokeragefee" value="${modelUpdate.brokeragefee}" ></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Ghi chú</p>
                                                <input class="form-control" type="text" id="note" name="note" value="${modelUpdate.note}" ></input>
                                            </div>
                                            <div class="col-md-12">
                                                <p>Link sản phẩm</p>
                                                <input class="form-control" type="text" id="linkofbuilding" name="linkofbuilding" value="${modelUpdate.linkofbuilding}" ></input>
                                            </div>
                                            <div class="col-md-12">
                                                <p>Bản đồ</p>
                                                <input class="form-control" type="text" id="map" name="map" value="${modelUpdate.map}"></input>
                                            </div>
                                            <div class="col-md-12" >
                                                <p>Hình đại diện</p>
                                                <input class="form-control" type="text" id="image" name="image"  value="${modelUpdate.image}"></input>
                                            </div>

                                            <div >
                                                <label>Loại tòa nhà</label>
                                                <br>
                                                <form:checkboxes path="types" items="${buildingTypes}"></form:checkboxes>
                                            </div>
                                            <input type="hidden" class="form-control" id="id" name="id" value="${modelUpdate.id}">
                                            <div class="text-center">
                                                <input type="button" name="insert_data" class="btn btn-primary" value="Thêm tòa nhà" id="btnAddBuilding"></input>
                                                <input type="reset" value="Reset" class="btn btn-secondary"></input>
                                            </div>
                                        </div>
                                    </form:form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    var modelId = $('#id').val();
    $('#btnAddBuilding').click(function (e) {
        e.preventDefault();
        var data = {};
        var formdata = $('#formData').serializeArray();
        var types = [];
        $.each(formdata,function (index,v) {
            if(v.name == 'types'){
                types.push(v.value);
            }else {
                data[""+v.name+""] = v.value;
            }
        });
        data['types'] = types;
        var building_id = $('#id').val();
        if (building_id != "") {
            $.ajax({
                type: 'POST',
                url: '${buildingAPI}/'+building_id+'',
                data: JSON.stringify(data),
                //dataType: "json",
                contentType:"application/json" ,
                success: function (response) {
                    $(location).attr("href", "${buildingListURL}");
                },
                error: function (response) {
                    alert("failed")
                }
            });
        }else {
            $.ajax({
                type: 'PUT',
                url: '${buildingAPI}',
                data: JSON.stringify(data),
                //dataType: "json",
                contentType:"application/json" ,
                success: function (response) {
                    $(location).attr("href", "${buildingListURL}");
                },
                error: function (response) {
                    alert("failed")
                }
            });
        }
    });

</script>
</body>
</html>
