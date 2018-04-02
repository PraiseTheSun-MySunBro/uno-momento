<template>
  <div class="div__faculty"
       v-if="$auth.check()">
    <h1 class="faculty__name">{{ faculty.eeName }}</h1>
  </div>
</template>

<script>
export default {
  name: 'TheFacultyName',
  props: {
    faculty: {}
  }
}
</script>

<style scoped>
.div__faculty {
  left:0;
  right:0;
  margin-left:auto;
  margin-right:auto;
  margin-top: 80px;
  padding: 0px 10px 0px 10px;
  width: max-content;
  height: max-content;
  max-width: 70%;
  text-align: center;

  background-color: rgba(0,0,0,.5);
  position: absolute;
}
.faculty__name {
  font-weight: 300;
  color: rgb(246, 249, 252);
  word-wrap: break-word;
  text-transform: uppercase;
}
</style>
