<template>
  <div class="container">
    <h1 class="title"><i class="fas fa-plus-circle icon"></i>Create Student</h1>
    <form @submit.prevent="handleSubmit" autocomplete="on">
      <div class="form-group">
        <label for="studentId">
          <i class="fas fa-id-card"></i> Student ID:
        </label>
        <input v-model="student.studentId" type="text" id="studentId" name="studentId" required autocomplete="student-id" placeholder="Enter student's ID" />
      </div>
      <div class="form-group">
        <label for="name">
          <i class="fas fa-user"></i> Name:
        </label>
        <input v-model="student.name" type="text" id="name" name="name" required autocomplete="name" placeholder="Enter student's name" />
      </div>
      <div class="form-group">
        <label for="age">
          <i class="fas fa-birthday-cake"></i> Age:
        </label>
        <input v-model="student.age" type="number" id="age" name="age" required autocomplete="age" placeholder="Enter student's age" />
      </div>
      <div class="form-group">
        <label for="dob">
          <i class="fas fa-calendar-alt"></i> Date of Birth:
        </label>
        <input v-model="student.dob" type="date" id="dob" name="dob" required autocomplete="bday" />
      </div>
      <div class="form-group">
        <label for="email">
          <i class="fas fa-envelope"></i> Email:
        </label>
        <input v-model="student.email" type="email" id="email" name="email" required autocomplete="email" placeholder="Enter student's email" />
      </div>
      <div class="form-actions">
        <button type="submit" :disabled="isSubmitting" class="submit-btn">
          Create Student
        </button>
        <button @click="goBack" type="button" class="back-btn">
          Back
        </button>
      </div>
    </form>

    <!-- Notification -->
    <div v-if="notificationMessage" :class="`notification ${notificationType}`">
      <p>{{ notificationMessage }}</p>
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import { ref } from 'vue';
import { useRouter } from 'vue-router';

export default {
  setup() {
    const router = useRouter();

    const student = ref({
      studentId: '',
      name: '',
      age: '',
      dob: '',
      email: ''
    });
    const isSubmitting = ref(false); 
    const notificationMessage = ref(''); 
    const notificationType = ref(''); 

    const handleSubmit = async () => {
      isSubmitting.value = true;
      notificationMessage.value = ''; 

      const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
      if (!emailRegex.test(student.value.email)) {
        showNotification('Please enter a valid email address. Example: example@gmail.com', 'error');
        isSubmitting.value = false;
        return;
      }

      try {
        const emailCheckResponse = await axios.get(`http://localhost:8080/students/check-email`, {
          params: { email: student.value.email }
        });

        if (emailCheckResponse.data.exists) {
          showNotification('Email already exists. Please use a different email.', 'error');
          isSubmitting.value = false;
          return;
        }

        // Create new student
        await axios.post('http://localhost:8080/students', student.value);
        showNotification('Student created successfully!', 'success');

        // Optionally, reset the form after creation
        student.value = {
          studentId: '',
          name: '',
          age: null,
          dob: '',
          email: ''
        };
      } catch (error) {
        console.error('Error submitting form:', error);
        showNotification('A student with the same Student ID or Email already exists.', 'error');
      } finally {
        isSubmitting.value = false; 
      }
    };

    const showNotification = (message, type) => {
      notificationMessage.value = message;
      notificationType.value = type;

      setTimeout(() => {
        notificationMessage.value = '';
        notificationType.value = '';
      }, 3000);
    };

    // Go back to the previous page
    const goBack = () => {
      router.go(-1); // This will navigate to the previous page
    };

    // Return the reactive variables and methods
    return {
      student,
      handleSubmit,
      isSubmitting,
      notificationMessage,
      notificationType,
      goBack,
    };
  },
};
</script>

<style scoped>
.container {
  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif;
  display: flex;
  flex-direction: column;
  align-items: center;
  max-width: 500px;
  margin: 0 auto;
  padding: 10px;
  background-color: #f0f4f8;
  border-radius: 8px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
  margin-top: 10px;
}

.title {
  display:flex;
  align-items: center;
  font-size: 24px;
  font-weight: bold;
  color: #00796B;
  margin-bottom: 20px;
  gap: 10px;
}

form {
  width: 100%;
}

.form-group {
  margin-bottom: 20px;
}

label {
  font-size: 14px;
  color: #333;
  margin-bottom: 8px;
  font-weight: bold;
  display: flex;
  align-items: center;
}

label i {
  margin-right: 10px;
  font-size: 18px;
}

input {
  width: 95%;
  padding: 8px;
  font-size: 14px;
  border-radius: 4px;
  border: 1px solid #ccc;
  background-color: #fff;
  transition: border-color 0.3s;
}

input:focus {
  border-color: #4caf50;
  outline: none;
}

.submit-btn {
  padding: 12px;
  font-size: 16px;
  background-color: #00796B;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.submit-btn:hover {
  background-color: #45a049;
}

.submit-btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.back-btn {
  padding: 12px;
  font-size: 16px;
  background-color: #2c3e50;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.back-btn:hover {
  background-color: #bbb;
}

.back-btn:disabled {
  background-color: #f0f0f0;
  cursor: not-allowed;
}

.notification {
  padding: 12px;
  font-size: 16px;
  border-radius: 4px;
  text-align: center;
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 9999;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}

.notification.success {
  background-color: #4caf50;
  color: white;
}

.notification.error {
  background-color: #f44336;
  color: white;
}

.form-actions {
  display: flex;
  justify-content: flex-end;
  gap: 10px;
  margin-top: 20px;
}
</style>
