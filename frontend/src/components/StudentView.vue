<template>
  <div class="student-view-container">
    <h1 class="header"><i class="fas fa-user-graduate"></i>Student Details</h1>
    <div v-if="student">
      <div class="student-info-card">
        <div class="info-item">
          <i class="fas fa-user icon"></i>
          <div>
            <strong>Name:</strong>
            <span>{{ student.name }}</span>
          </div>
        </div>
        <div class="info-item">
          <i class="fas fa-id-badge icon"></i>
          <div>
            <strong>Student ID:</strong>
            <span>{{ student.studentId }}</span>
          </div>
        </div>
        <div class="info-item">
          <i class="fas fa-calendar-alt icon"></i>
          <div>
            <strong>Age:</strong>
            <span>{{ student.age }} years old</span>
          </div>
        </div>
        <div class="info-item">
          <i class="fas fa-birthday-cake icon"></i>
          <div>
            <strong>Date of Birth:</strong>
            <span>{{ formatDate(student.dob) }}</span>
          </div>
        </div>
        <div class="info-item">
          <i class="fas fa-envelope icon"></i>
          <div>
            <strong>Email:</strong>
            <span>{{ student.email }}</span>
          </div>
        </div>
      </div>

      <div class="back-btn-container">
        <button @click="goBack" class="back-btn">
          <i class="fas fa-arrow-left"></i> Back to List
        </button>
      </div>
    </div>

    <div v-else class="loading-error">
      <p>Loading student details...</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  data() {
    return {
      student: null,
    };
  },
  methods: {
    async fetchStudent() {
      const studentId = this.$route.params.id;
      try {
        const response = await axios.get(`http://localhost:8080/students/${studentId}`);
        this.student = response.data; 
      } catch (error) {
        console.error('Error fetching student details:', error);
      }
    },
    goBack() {
      this.$router.push('/'); 
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
  },
  mounted() {
    this.fetchStudent(); 
  },
};
</script>

<style scoped>
body {
  overflow-x: hidden;
}

.student-view-container {
  max-width: 600px;
  width: 90%;
  margin: 10px auto;
  padding: 20px;
  background: linear-gradient(135deg, #f5f7fa, #e4e9f2);
  border-radius: 15px;
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  text-align: center;
  box-sizing: border-box;
  overflow: hidden;
}

.header {
  display: flex;
  justify-content: center;
  align-items: center;
  gap: 10px;
  font-size: 2rem;
  font-weight: 600;
  margin-bottom: 20px;
  color: #2c3e50;
  word-wrap: break-word;
}

.student-info-card {
  padding: 10px;
  background: #fff;
  border-radius: 12px;
  box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
  box-sizing: border-box;
}

.info-item {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
  font-size: 18px;
  color: #34495e;
  justify-content: flex-start;
}

.info-item div {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
}

.info-item strong {
  color: #2c3e50;
  font-weight: 600;
}

.icon {
  margin-right: 15px;
  color: #3498db;
  font-size: 1.5rem;
  width: 35px;
  text-align: center;
}

.back-btn-container {
  text-align: center;
}

.back-btn {
  padding: 12px 24px;
  background-color: #2c3e50;
  color: white;
  font-size: 1rem;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  margin-top: 20px;
}

.back-btn:hover {
  background-color: #2980b9;
  transform: translateY(-2px);
  box-shadow: 0 6px 18px rgba(0, 0, 0, 0.2);
}

.back-btn i {
  margin-right: 10px;
}

.loading-error {
  text-align: center;
  font-size: 1rem;
  color: #888;
  margin-top: 20px;
}

/* Media Query for Mobile */
@media (max-width: 768px) {
  .student-view-container {
    width: 90%;
    padding: 15px;
  }

  .header {
    font-size: 1.75rem;
    margin-bottom: 15px;
  }

  .student-info-card {
    padding: 15px;
  }

  .info-item {
    font-size: 1rem;
    margin-bottom: 12px;
  }

  .back-btn {
    font-size: 0.9rem;
    padding: 10px 20px;
  }
}

/* Media Query for Larger Screens (Tablet/Small Desktop) */
@media (min-width: 769px) and (max-width: 1024px) {
  .student-view-container {
    width: 80%;
  }

  .header {
    font-size: 1.85rem;
  }

  .student-info-card {
    padding: 12px;
  }
}
</style>
