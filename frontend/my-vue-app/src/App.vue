<template>
  <div id="app">
    <nav>
      <!-- Ссылки для перехода между страницами -->
      <router-link to="/">Главная</router-link>
      <router-link to="/all-birthdays">Все дни рождения</router-link>
    </nav>
    <router-view />
  </div>
</template>

<script>
export default {
  name: 'App',
};
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  text-align: center;
  color: #2c3e50;
}

nav {
  display: flex;
  justify-content: center;
  gap: 20px;
  margin-bottom: 20px;
}

nav a {
  text-decoration: none;
  color: #2c3e50;
  font-weight: bold;
}

nav a.router-link-active {
  color: #42b983;
}
</style>
