<template>
  <div class="birthday-card">
    <img :src="birthday.photoUrl" alt="Фото" class="birthday-photo" v-if="birthday.photoUrl" />
    <div class="birthday-info">
      <h3>{{ birthday.name }} {{ birthday.surname }}</h3>
      <p>Дата рождения: {{ formattedDate }}</p>
      <button @click="showPutBirthday = !showPutBirthday">Изменить информацию о ДР</button>
      <button @click="deleteBirthday">Удалить день рождения</button>
    </div>
    <PutBirthday
      v-if="showPutBirthday"
      :birthday="formattedBirthday"
      @updated="handleUpdate"
      @cancel="showPutBirthday = false"
    />
  </div>
</template>

<script>
import axios from "axios";
import PutBirthday from "./PutBirthday.vue";

export default {
  props: {
    birthday: {
      type: Object,
      required: true,
    },
  },
  components: {
    PutBirthday,
  },
  data() {
    return {
      showPutBirthday: false,
    };
  },
  computed: {
    formattedDate() {
      const date = new Date(this.birthday.birthdayDate);
      return `${date.getDate()}/${date.getMonth() + 1}/${date.getFullYear()}`;
    },
    formattedBirthday() {
      const date = new Date(this.birthday.birthdayDate);
      const year = date.getFullYear();
      const month = String(date.getMonth() + 1).padStart(2, "0");
      const day = String(date.getDate()).padStart(2, "0");
      return { ...this.birthday, birthdayDate: `${year}-${month}-${day}` };
    },
  },
  methods: {
    async deleteBirthday() {
      try {
        const response = await axios.delete(
          `http://localhost:8080/${this.birthday.id}`
        );
        console.log("Удалено успешно", response.data);
      } catch (error) {
        console.error("Ошибка при удалении ДР: ", error);
      }
    },
    handleUpdate(updatedBirthday) {
      this.showPutBirthday = false;
      Object.assign(this.birthday, updatedBirthday);
    },
  },
};
</script>


<style scoped>
.birthday-card {
  background: linear-gradient(145deg, #ffffff, #f0f0f0);
  border-radius: 20px;
  box-shadow: 10px 10px 30px #d1d1d1, -10px -10px 30px #ffffff;
  padding: 20px;
  max-width: 350px;
  margin: 20px auto;
  transition: transform 0.3s ease, box-shadow 0.3s ease;
}

.birthday-card:hover {
  transform: translateY(-5px);
  box-shadow: 12px 12px 35px #c1c1c1, -12px -12px 35px #ffffff;
}

.birthday-photo {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 15px;
  margin-bottom: 15px;
}

.birthday-info {
  text-align: center;
}

h3 {
  color: #333;
  font-size: 1.5em;
  margin-bottom: 10px;
}

p {
  color: #666;
  font-size: 1em;
  margin-bottom: 20px;
}

button {
  background-color: #e21f1f;
  border: none;
  color: white;
  padding: 10px 20px;
  text-align: center;
  text-decoration: none;
  display: inline-block;
  font-size: 16px;
  margin: 4px 2px;
  transition-duration: 0.4s;
  cursor: pointer;
  border-radius: 12px;
}

button:first-of-type {
  background-color: #f4c836;
}

button:hover {
  opacity: 0.8;
}
</style>