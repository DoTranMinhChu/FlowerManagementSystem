

<nav class="navbar">
    <div class="nav-list">
        <div class="nav-item"><a href="../" class="nav-link">Home</a></div>
        <div class="nav-item"><a href="manageAccounts" class="nav-link">Manage Accounts</a></div>
        <div class="nav-item"><a href="manageOrders" class="nav-link">Manage Orders</a></div>
        <div class="nav-item"><a href="managePlants" class="nav-link">Manage Plants</a></div>
        <div class="nav-item"><a href="manageCategories" class="nav-link">Manage Categories</a></div>

    </div>
    <div class="nav-list">
        <div class="nav-item">Welcome ${sessionScope.account.getFullName()}   | <a href="?action=../logout&page=../" class="nav-link">Logout</a></div>
    </div>


</nav>


