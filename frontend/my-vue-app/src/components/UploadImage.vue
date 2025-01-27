<template>
    <div class="upload-container">
      <h3>Загрузите изображение</h3>
      <input type="file" accept="image/*" @change="handleFileUpload" />
      <p v-if="imageUrl">Ссылка на изображение: <a :href="imageUrl" target="_blank">{{ imageUrl }}</a></p>
      <img :src="imageUrl" alt="Загруженное изображение" v-if="imageUrl" class="uploaded-image" />
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  
  export default {
    data() {
      return {
        imageUrl: null, 
      };
    },
    methods: {
      async handleFileUpload(event) {
        const file = event.target.files[0];
        if (!file) {
          alert("Выберите файл!");
          return;
        }
  
        const formData = new FormData();
        formData.append('file', file);
  
        try {
          const response = await axios.post('http://localhost:8080/api/files/upload', formData, {
            headers: {
              'Content-Type': 'multipart/form-data',
            },
          });
  
          this.imageUrl = response.data; 
        } catch (error) {
          console.error('Ошибка при загрузке файла:', error);
          alert('Ошибка при загрузке файла');
        }
      },
    },
  };
  </script>
  
  <style scoped>
  .upload-container {
    text-align: center;
    margin-top: 20px;
  }
  
  .uploaded-image {
    max-width: 100%;
    height: auto;
    margin-top: 10px;
    border-radius: 8px;
  }
  </style>