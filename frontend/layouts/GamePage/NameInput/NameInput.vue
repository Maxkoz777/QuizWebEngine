<template>
  <div class="bg-white q-pa-lg name-input-container">
    <q-input color="purple" label-color="dark-purple" class="name-input" filled label="ENTER USERNAME"/>
    <q-btn class="full-width name-button q-mt-sm" unelevated>
      ENTER
    </q-btn>
  </div>
</template>

<script>

export default {
  name: "NameInput",
}
</script>

<style scoped>
.name-input {
  background-color: #d8bfe7;
  border-radius: 5px;
}

.name-input-container {
  border-radius: 5px;
  width: 18vw;
}

.name-button {
  background-color: #15054F;
  color: white;
  border-radius: 5px;
}
</style>
