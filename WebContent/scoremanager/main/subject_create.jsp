<%-- 科目登録JSP --%>
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
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">科目情報登録</h2>
			<%-- パラメーターdoneが存在する場合 --%>
			<c:if test="${!empty done}">
				<div class="bg-success bg-opacity-50 text-center lh-lg">
					<p>${done}</p>
				</div>
			</c:if>
			<form action = "SubjectCreateExecute.action" method="post">
				<div class="mx-3 py-2">
					<div class="mb-3">
						<label class="form-label" for="subject-cd-input">科目コード</label>
						<input class="form-control" type="text" id="subject-cd-input" name="cd"
							placeholder="科目コードを入力してください" maxlength="10" value="${cd}" required />
						<div class="mt-2 text-warning">${errors.get("cd")}</div>
					<div class="mt-2 text-warning">${errors.get("cd")}</div>
					</div>
					<div class="my-3">
						<label class="form-label" for="subject-name-input">科目名</label>
						<input class="form-control" type="text" id="subject-name-input" name="name"
							placeholder="科目を入力してください" maxlength="10" value="${name}" required />
						<div class="mt-2 text-warning">${errors.get("name")}</div>

					<div class="mt-3">
					<!--
						<input class="btn btn-primary" type="submit" value="登録して再度入力" name="continue">
					 -->
						<input class="btn btn-secondary" type="submit" value="登録して終了" name="end">
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