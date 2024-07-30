<%-- 科目変更JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報変更</h2>
			<form action = "StudentUpdateExecute.action" method="post">
				<div class="mx-3 py-2">
					<div class="mb-3">
						<label class="form-label" for="student-ent_year-input">科目名</label>
						<input readonly class="form-control-plaintext ms-3" type="text"
							id="subject-cd-input" name="cd" value="${cd}" />
						<select class="form-select" id="student-ent_year-select" name="ent_year">
							<option value="0">--------</option>

						</select>
					</div>

					<div class="mt-3">
						<input class="btn btn-primary" type="submit" value="変更">
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