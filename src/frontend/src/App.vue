<template>
  <div id="app" v-cloak v-if="$auth.ready()">
    <router-view/>
  </div>
  <div v-else>
    <h1>Loading...</h1>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 60px;
  }

  [v-cloak] > * { display: none; }
</style>
