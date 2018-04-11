<template>
  <div class="error">
    <b-container class="container__error">
      <div class="mt-4 mb-3">
        <b-img center
               :src="error"
               fluid/>
      </div>
    </b-container>
  </div>
</template>

<script>
export default {
  name: 'PageNotFound',
  data () {
    return {
      error: require('../assets/404.png')
    }
  }
}
</script>

<style scoped>
.error {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.container__error {
  overflow: hidden;
  position: relative;
  width: 100%;
  height: 100%;
  min-width: 262px;
  margin: 0 auto;
}
</style>
