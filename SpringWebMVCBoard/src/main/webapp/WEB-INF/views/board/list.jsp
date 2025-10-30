<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
          crossorigin="anonymous">
    <title>Board List Page</title>
</head>
<body>

<div class="container-fluid">
    <!-- Header / Navbar -->
    <div class="row">
        <div class="col">
            <nav class="navbar navbar-expand-lg navbar-light bg-light">
                <div class="container-fluid">
                    <a class="navbar-brand" href="#">Board</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
                            aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link active" href="#">Home</a>
                            <a class="nav-link" href="#">Write</a>
                        </div>
                    </div>
                </div>
            </nav>
        </div>
    </div>
    <!-- Header end -->

    <!-- Search Form -->
    <div class="row content mt-3">
        <div class="col">
            <div class="card">
                <div class="card-body">
                    <h5 class="card-title">검색</h5>
                    <form action="/board/list" method="get">
                        <div class="mb-3">
                            <input type="text" name="keyword" class="form-control"
                                   placeholder="Search title or writer"
                                   value='<c:out value="${pageRequestDTO.keyword}"/>'>
                        </div>
                        <div class="input-group mb-3">
                            <div class="float-end">
                                <button class="btn btn-primary" type="submit">Search</button>
                                <button class="btn btn-info clearBtn" type="reset">Clear</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- Board Table -->
    <div class="row content mt-3">
        <div class="col">
            <div class="card">
                <div class="card-header">
                    Board List
                </div>
                <div class="card-body">
                    <table class="table table-striped">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">글 제목</th>
                            <th scope="col">작성자</th>
                            <th scope="col">작성일시</th>
                            <th scope="col">최종수정일시</th>
                            <th scope="col">조회수</th>
                            <%--                            <th scope="col">Likes</th>--%>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${boardDTOList}" var="dto">
                            <tr>
                                <td>
                                    <a href="/board/read?board_Id=${dto.board_Id}">
                                        <c:out value="${dto.board_Id}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="/board/read?board_Id=${dto.board_Id}">
                                        <c:out value="${dto.title}"/>
                                    </a>
                                </td>
                                <td><c:out value="${dto.writer}"/></td>
                                <td><c:out value="${dto.created_At}"/></td>
                                <td><c:out value="${dto.updated_At}"/></td>
                                <td><c:out value="${dto.viewcount}"/></td>
                                    <%--                                <td><c:out value="${dto.likecount}"/></td>--%>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>

                    <!-- Pagination -->
                    <div class="float-end">
                        <ul class="pagination flex-wrap">
                            <c:if test="${responseDTO.prev}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.start -1}">Previous</a>
                                </li>
                            </c:if>

                            <c:forEach begin="${responseDTO.start}" end="${responseDTO.end}" var="num">
                                <li class="page-item ${responseDTO.page == num ? "active":""}">
                                    <a class="page-link" data-num="${num}">${num}</a>
                                </li>
                            </c:forEach>

                            <c:if test="${responseDTO.next}">
                                <li class="page-item">
                                    <a class="page-link" data-num="${responseDTO.end + 1}">Next</a>
                                </li>
                            </c:if>
                        </ul>
                    </div>

                    <script>
                        // Pagination 클릭 이벤트
                        document.querySelector(".pagination").addEventListener("click", function (e) {
                            e.preventDefault()
                            e.stopPropagation()
                            const target = e.target
                            if (target.tagName !== 'A') return
                            const num = target.getAttribute("data-num")
                            const formObj = document.querySelector("form")
                            formObj.innerHTML += `<input type='hidden' name='page' value='${num}'>`
                            formObj.submit();
                        }, false)

                        // Clear 버튼
                        document.querySelector(".clearBtn").addEventListener("click", function (e) {
                            e.preventDefault()
                            e.stopPropagation()
                            self.location = '/board/list'
                        }, false)
                    </script>

                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <div class="row footer mt-3">
        <div class="row fixed-bottom" style="z-index: -100">
            <footer class="py-1 my-1">
                <p class="text-center text-muted">Footer</p>
            </footer>
        </div>
    </div>

</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
</body>
</html>
