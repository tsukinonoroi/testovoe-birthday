<template>
  <div class="birthday-reminder">
    <button @click="showAddBirthdayForm = !showAddBirthdayForm">
      Добавить день рождения
    </button>

    <div v-if="showAddBirthdayForm">
      <AddBirthday />
    </div>

    <h1>Дни Рождения</h1>

    <h2>Сегодня:</h2>
    <div v-if="todayBirthdays.length" class="birthday-container">
      <BirthdayCard
        v-for="birthday in todayBirthdays"
        :key="birthday.id"
        :birthday="birthday"
        :class="['birthday-card', 'today-card']"
      />
    </div>
    <p v-else>Сегодня нет дней рождений.</p>

    <h2>Ближайшие дни рождения:</h2>
    <div v-if="upcomingBirthdays.length" class="birthday-container">
      <BirthdayCard
        v-for="birthday in upcomingBirthdays"
        :key="birthday.id"
        :birthday="birthday"
        class="birthday-card"
      />
    </div>
    <p v-else>Нет ближайших дней рождений.</p>
  </div>
</template>


<script>
import axios from 'axios';
import AddBirthday from './AddBirthday.vue';
import BirthdayCard from './BirthdayCard.vue';

export default {
  data() {
    return {
      todayBirthdays: [],
      upcomingBirthdays: [],
      showAddBirthdayForm: false,
    };
  },
  mounted() {
    this.fetchBirthdays();
  },
  methods: {
    async fetchBirthdays() {
      try {
        const response = await axios.get('http://localhost:8080/');
        const allBirthdays = response.data;

        const today = new Date();
        const upcomingDate = new Date();
        upcomingDate.setDate(today.getDate() + 7);

        
        this.todayBirthdays = allBirthdays.filter((birthday) => {
          const birthdayDate = new Date(birthday.birthdayDate);
          return (
            birthdayDate.getDate() === today.getDate() &&
            birthdayDate.getMonth() === today.getMonth()
          );
        });

        
        this.upcomingBirthdays = allBirthdays.filter((birthday) => {
          const birthdayDate = new Date(birthday.birthdayDate);

          
          birthdayDate.setFullYear(today.getFullYear());

          return birthdayDate > today && birthdayDate <= upcomingDate;
        });
      } catch (error) {
        console.error('Ошибка при получении данных о днях рождения:', error);
      }
    },
  },
  components: {
    AddBirthday,
    BirthdayCard,
  },
};
</script>

<style scoped>

button {
  padding: 10px 20px;
  background-color: #4caf50;
  color: white;
  border: none;
  border-radius: 5px;
  font-size: 16px;
  cursor: pointer;
  margin-bottom: 20px;
}

button:hover {
  background-color: #45a049;
}


.birthday-container {
  display: flex;
  flex-wrap: wrap; 
  justify-content: center; 
  gap: 20px; 
  margin-top: 20px;
}


.birthday-card {
  flex: 1 1 calc(25% - 20px); 
  max-width: 250px; 
  box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
  padding: 10px;
  border-radius: 8px;
  background-color: #f9f9f9;
  text-align: center;
}

@media (max-width: 768px) {
  .birthday-card {
    flex: 1 1 calc(50% - 20px);
  }
}

@media (max-width: 480px) {
  .birthday-card {
    flex: 1 1 100%; 
  }
}

h1 {
  font-size: 24px;
  font-weight: bold;
  margin-top: 20px;
}

h2 {
  font-size: 20px;
  font-weight: bold;
  margin-top: 20px;
}
</style>
