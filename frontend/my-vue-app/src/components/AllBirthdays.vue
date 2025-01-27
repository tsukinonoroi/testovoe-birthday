<template>
  <div>
    <h1>Все дни рождения</h1>
    <div>
      <label for="month">Выберите месяц:</label>
      <select id="month" v-model="selectedMonth" @change="sortBirthdays">
        <option value="">Все месяцы</option>
        <option v-for="month in months" :key="month" :value="month">{{ monthNames[month] }}</option>
      </select>
    </div>

    <div v-if="birthdays.length">
      <div class="birthday-container">
        <BirthdayCard
          v-for="birthday in sortedBirthdays"
          :key="birthday.id"
          :birthday="birthday"
          class="birthday-card"
        />
      </div>
    </div>
    <p v-else>Нет доступных данных о днях рождения.</p>
  </div>
</template>

<script>
import axios from 'axios';
import BirthdayCard from './BirthdayCard.vue';

export default {
  data() {
    return {
      birthdays: [], 
      selectedMonth: '', 
      sortedBirthdays: [], 
      months: Array.from({ length: 12 }, (_, index) => index), 
      monthNames: [
        'Январь', 'Февраль', 'Март', 'Апрель', 'Май', 'Июнь',
        'Июль', 'Август', 'Сентябрь', 'Октябрь', 'Ноябрь', 'Декабрь'
      ], 
    };
  },
  mounted() {
    this.fetchAllBirthdays();
  },
  methods: {
    
    async fetchAllBirthdays() {
      try {
        const response = await axios.get('http://localhost:8080/');
        this.birthdays = response.data;
        this.sortBirthdays(); 
      } catch (error) {
        console.error('Ошибка при загрузке всех дней рождения:', error);
      }
    },
    
    sortBirthdays() {
      if (this.selectedMonth !== '') {
        this.sortedBirthdays = this.birthdays.filter(birthday => {
          const month = new Date(birthday.birthdayDate).getMonth();
          return month === parseInt(this.selectedMonth);
        });
      } else {
        this.sortedBirthdays = [...this.birthdays]; 
      }
    },
  },
  components: {
    BirthdayCard,
  },
};
</script>

<style scoped>

.birthday-container {
  display: flex;
  flex-wrap: wrap; 
  justify-content: center; 
  gap: 20px; 
  margin-top: 20px;
}

.birthday-card {
  flex: 1 1 calc(20% - 20px); 
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
  margin-bottom: 20px;
}

select {
  padding: 5px;
  font-size: 16px;
  margin-top: 10px;
}
</style>
