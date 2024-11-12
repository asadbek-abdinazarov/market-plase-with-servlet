<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm py-3">
  <div class="container-fluid">
    <a class="navbar-brand" href="/home" style="font-weight: bold; color: #4e54c8;">Home</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse justify-content-end" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="/product/list" style="font-weight: 500; color: #333; transition: color 0.3s;">Products</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="/contacts" style="font-weight: 500; color: #333; transition: color 0.3s;">Contacts</a>
        </li>
        <!-- Basket Icon -->
        <li class="nav-item">
          <a class="nav-link" href="/basket" style="display: flex; align-items: center;">
            <img src="/imgs/basket_16933260.png" alt="Basket" style="height: 25px; width: 25px; margin-right: 5px;">
            <span style="font-weight: 500; color: #333;">Basket</span>
          </a>
        </li>
      </ul>
    </div>
  </div>
</nav>

<style>
  .navbar-nav .nav-link {
    position: relative;
    font-size: 1rem;
    margin: 0 10px;
    padding-bottom: 5px;
    transition: color 0.3s ease, transform 0.3s ease;
  }

  .navbar-nav .nav-link:hover {
    color: #4e54c8;
    transform: translateY(-2px);
  }

  /* Active link styling */
  .navbar-nav .nav-link.active {
    color: #4e54c8;
    font-weight: bold;
    border-bottom: 2px solid #4e54c8;
  }

  /* Basket icon styling */
  .navbar-nav .nav-link img {
    transition: transform 0.3s ease;
  }

  .navbar-nav .nav-link:hover img {
    transform: rotate(-20deg) scale(1.1);
  }
</style>