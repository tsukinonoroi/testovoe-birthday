<template>
  <div>
    <form @submit.prevent="submitForm">
      <div>
        <label for="name">Имя</label>
        <input type="text" v-model="name" id="name" required />
      </div>
      <div>
        <label for="surname">Фамилия</label>
        <input type="text" v-model="surname" id="surname" required />
      </div>
      <div>
        <label for="birthdayDate">Дата рождения</label>
        <input type="date" v-model="birthdayDate" id="birthdayDate" required />
      </div>
      <div>
        <h3>Загрузите изображение</h3>
        <input type="file" accept="image/*" @change="handleFileUpload" />
        <p v-if="photoUrl">Ссылка на изображение: <a :href="photoUrl" target="_blank">{{ photoUrl }}</a></p>
        <img :src="photoUrl" alt="Загруженное изображение" v-if="photoUrl" class="uploaded-image" />
      </div>

      <button type="submit">Сохранить изменения</button>
    </form>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  props: {
    birthday: {
      type: Object,
      required: true,
    },
  },
  data() {
    return {
      name: this.birthday.name || '',
      surname: this.birthday.surname || '',
      birthdayDate: this.birthday.birthdayDate || '',
      photoUrl: this.birthday.photoUrl || '', 
    };
  },
  methods: {
    async handleFileUpload(event) {
      const file = event.target.files[0];
      if (!file) {
        alert('Выберите файл!');
        return;
      }

      const formData = new FormData();
      formData.append('file', file);

      try {
        const response = await axios.post('http://localhost:8080/upload', formData, {
          headers: {
            'Content-Type': 'multipart/form-data',
          },
        });

        if (response.data.url) {
          this.photoUrl = response.data.url;
        } else {
          alert('Ошибка загрузки файла!');
        }
      } catch (error) {
        console.error('Ошибка при загрузке:', error);
        alert('Ошибка при загрузке файла');
      }
    },

    async submitForm() {
      try {
        const updatedData = {
          name: this.name,
          surname: this.surname,
          birthdayDate: this.birthdayDate,
          photoUrl: this.photoUrl, 
        };

        await axios.put(`http://localhost:8080/${this.birthday.id}`, updatedData, {
          headers: { 'Content-Type': 'application/json' },
        });

        this.$emit('update-birthday', updatedData);
      } catch (error) {
        console.error('Ошибка при обновлении данных:', error);
      }
    },
  },
};
</script>

<style scoped>
form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  background-color: #f9f9f9;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

form div {
  margin-bottom: 16px;
}

label {
  display: block;
  margin-bottom: 8px;
  font-weight: bold;
  font-size: 14px;
  color: #333;
}

input {
  width: 100%;
  padding: 8px;
  font-size: 14px;
  border: 1px solid #ccc;
  border-radius: 4px;
  box-sizing: border-box;
}

button {
  display: block;
  width: 100%;
  padding: 10px 16px;
  font-size: 16px;
  font-weight: bold;
  color: white;
  background-color: #007bff;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #0056b3;
}

.uploaded-image {
  max-width: 100%;
  height: auto;
  margin-top: 10px;
  border-radius: 8px;
}
</style>
