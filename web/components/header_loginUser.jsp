
<nav class="navbar">
    <div class="nav-list">
        <div class="nav-item"><a href="../" class="nav-link">Home</a></div>
        <div class="nav-item" onclick="clickShow('profileFormBox')"><a href="" class="nav-link none-events">Profile</a></div>
        <div class="nav-item"><a href="personalPage" class="nav-link">View all orders</a></div>

    </div>
    <div class="nav-list">
        <div class="nav-item">Welcome ${sessionScope.account.getFullName()}   | <a href="?action=../logout&page=../" class="nav-link">Logout</a></div>  
    </div>
</nav>





