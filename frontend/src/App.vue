<template>
  <div class="min-h-screen bg-gray-50 text-gray-800">
    <header class="bg-blue-600 text-white py-4 shadow">
      <div class="container mx-auto px-4 flex justify-between items-center">
        <h1 class="header">🎓 Student CRUD</h1>
      </div>
    </header>

    <main class="container mx-auto px-4 py-6">
      <router-view></router-view>
    </main>

    <footer class="footer">
      &copy; 2025 Student CRUD
    </footer>
  </div>
</template>


<style scoped>
header{
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  display:flex;
  justify-content: center;
}

footer{
  font-family:'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  display:flex;
  justify-content:center;
}
</style>
