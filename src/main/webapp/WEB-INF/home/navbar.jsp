<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!-- Include CSS for styling -->
<style><%@include file="./styles/navbar.css"%></style>

<!-- Navbar -->
<div class="navbar_wrapper itim-regular">
    <div>
        <form action="home" method="post" style="display:inline;">
            <input type="hidden" name="section" value="Announcements">
            <button type="submit" class="itim-regular <%= "Announcements".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Announcements</button>
        </form>
        <form action="home" method="post" style="display:inline;">
            <input type="hidden" name="section" value="Popular Quizzes">
            <button type="submit" class="itim-regular <%= "Popular Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Popular Quizzes</button>
        </form>
        <form action="home" method="post" style="display:inline;">
            <input type="hidden" name="section" value="Friends Activities">
            <button type="submit" class="itim-regular <%= "Friends Activities".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Friends Activities</button>
        </form>
        <form action="home" method="post" style="display:inline;">
            <input type="hidden" name="section" value="Recently Created Quizzes">
            <button type="submit" class="itim-regular <%= "Recently Created Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">Recently Created Quizzes</button>
        </form>
    </div>
    <div>
        <form action="home" method="post" style="display:inline;">
            <input type="hidden" name="section" value="My Recently Taken Quizzes">
            <button type="submit" class="itim-regular <%= "My Recently Taken Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">My Recently taken Quizzes</button>
        </form>
        <form action="home" method="post" style="display:inline;">
            <input type="hidden" name="section" value="My Recently Created Quizzes">
            <button type="submit" class="itim-regular <%= "My Recently Created Quizzes".equals(request.getAttribute("currentSection")) ? "decorate_underline" : "" %>">My Recently Created Quizzes</button>
        </form>
    </div>
</div>
