<%-- 科目削除JSP --%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="scripts"></c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報削除</h2>
			<form action = "StudentUpdateExecute.action" method="post">
				<div class="mx-3 py-2">
					<div class="mb-3">
						<label class="form-label" for="student-ent_year-input">科目名</label>
						<input readonly class="form-control-plaintext ms-3" type="text"
							id="student-ent_year-input" name="ent_year" value="${ent_year}" />
						<select class="form-select" id="student-ent_year-select" name="ent_year">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ent_year_set}">
								<%-- 現在のyearと選択されていたent_yearが一致していた場合selectedを追記 --%>
								<option value="${year}" <c:if test="${year==ent_year}">selected</c:if>>${year}</option>
							</c:forEach>
						</select>
					</div>

					<div class="mt-3">
						<input class="btn btn-primary" type="submit" value="削除">
					</div>
				</div>
			</form>
			<div class="lh-lg row">
				<div class="mx-3 col-1">
					<a href="subject_list.jsp">戻る</a>
				</div>
			</div>
		</section>
	</c:param>
</c:import>