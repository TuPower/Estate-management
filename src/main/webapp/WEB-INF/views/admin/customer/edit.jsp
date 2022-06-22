<%--
  Created by IntelliJ IDEA.
  User: congt
  Date: 6/8/2022
  Time: 5:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/common/taglib.jsp"%>
<c:url var="customerAPI" value="/api/customer"></c:url>
<c:url var="customerEditURL" value="/admin/customer-edit"></c:url>
<c:url var="customerListURL" value="/admin/customer-list"></c:url>
<c:url var="transactionAPI" value="/api/customer/transaction"></c:url>
<html>
<head>
    <title>Danh sách khách hàng</title>
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
                Edit khách hàng
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
                                            <table class="table table-striped">
                                                <tr>
                                                    <td>Tên đầy đủ</td>
                                                    <td><input class="form-control" type="text" id="name" name="name" value="${customerModel.name}"></input></td>
                                                </tr>
                                                <tr>
                                                    <td>Số điện thoại</td>
                                                    <td><input class="form-control" type="text" id="phone" name="phone" value="${customerModel.phone}"></input></td>
                                                </tr>
                                                <tr>
                                                    <td>Email</td>
                                                    <td><input class="form-control" type="text" id="email" name="email" value="${customerModel.email}"></input></td>
                                                </tr>
                                                <tr>
                                                    <td>Tên công ty</td>
                                                    <td><input class="form-control" type="text" id="company" name="company" value="${customerModel.company}"></input></td>
                                                </tr>
                                                <tr>
                                                    <td>Nhu cầu</td>
                                                    <td><input class="form-control" type="text" id="needs" name="needs" value="${customerModel.needs}"></input></td>
                                                </tr>
                                                <tr>
                                                    <td>Ghi chú</td>
                                                    <td><input class="form-control" type="text" id="note" name="note" value="${customerModel.note}"></input></td>
                                                </tr>

                                            </table>
                                            <%--<input type="hidden" class="form-control" id="createdBy" name="createdBy" value="${customerModel.createdBy}">--%>
                                            <%--<input type="hidden" class="form-control" id="createdDate" name="createdDate" value="${customerModel.createdDate}">--%>
                                            <input type="hidden" class="form-control" id="id" name="id" value="${customerModel.id}">
                                            <div class="text-center">
                                                <input type="button" name="insert_data" class="btn btn-primary" value="Cập nhập khách hàng" id="btnAddCustomer"></input>
                                                <input type="reset" value="Reset" class="btn btn-secondary"></input>
                                            </div>
                                            <div id="transactionType">
                                                <c:forEach var="i" items="${transaction}">
                                                    <form class="form-horizontal form-control" id="transactions">
                                                        <div class="row container">
                                                            <h2 >
                                                                    ${i.code}
                                                                        <button type="button" id="btnAddTransaction" >
                                                                            <span>
                                                                                <i class="fa fa-plus-circle bigger-110 purple"></i>
                                                                            </span>
                                                                        </button>
                                                            </h2>
                                                        </div>
                                                        <table class="table table-striped text-center">
                                                            <thead>
                                                                <tr>
                                                                    <th>Ngày tạo</th>
                                                                    <th>Ghi chú</th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td>${i.createdDate}</td>
                                                                    <td>${i.note}</td>
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td>
                                                                        <input class="form-control" type="text" id="noteOfTrans" name="noteOfTrans" value=""></input>
                                                                    </td>
                                                                </tr>
                                                                <tr>
                                                                    <td></td>
                                                                    <td>
                                                                        <input class="form-control" type="hidden" id="codeOfTrans" name="codeOfTrans" value="${i.code}"></input>
                                                                    </td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </form>
                                                </c:forEach>
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
<script>
    $('#btnAddCustomer').click(function (e){
        e.preventDefault();
        var data = {};
        var formData = $('#formData').serializeArray();
        $.each(formData,function (index,v) {
            data[""+v.name+""] = v.value;
        });
        var customer_id = $('#id').val();
        if (customer_id != "") {
            $.ajax({
                type: 'POST',
                url: '${customerAPI}/'+customer_id+'',
                data: JSON.stringify(data),
                //dataType: "json",
                contentType:"application/json" ,
                success: function (response) {
                    $(location).attr("href", "${customerListURL}");
                },
                error: function (response) {
                    alert("sai")
                }
            });
        }else {
            $.ajax({
                type: 'PUT',
                url: '${customerAPI}',
                data: JSON.stringify(data),
                //dataType: "json",
                contentType:"application/json" ,
                success: function (response) {
                    alert("Thêm thành công")
                    $(location).attr("href", "${customerListURL}");
                },
                error: function (response) {
                    alert("failed")
                }
            });
        }
    });

    $('#btnAddTransaction').click(function (e){
        e.preventDefault()
        var data = {};
        var formdata = $('#transactions').serializeArray();
        $.each(formdata,function (index,v) {
            data[""+v.name+""] = v.value;
        });
        var customer_id = customer_id;
        $.ajax({
            type: 'PUT',
            url: '${transactionAPI}/'+customer_id+'',
            data: JSON.stringify(data),
            //dataType: "json",
            contentType:"application/json" ,
            success: function (response) {
                $(location).attr("href", "${customerEditURL}");
            },
            error: function (response) {
                alert("sai")
            }
        });
    }
</script>
</body>
</html>
