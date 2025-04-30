<template>
  <div class="container">
    <div class="header-container">
      <h1 class="header">
        <i class="fas fa-list"></i> Student List
      </h1>
      <button @click="navigateTo('/form')" class="btn primary-btn">
        <i class="fas fa-plus"></i> Create Student
      </button>
    </div>

    <div class="controls-container">
      <input
        v-model.trim="searchQuery"
        type="text"
        placeholder="Search"
        class="search-input"
      />
      <div class="students-per-page">
        <label for="studentsPerPage">Students per page:</label>
        <select v-model="studentsPerPage" id="studentsPerPage" class="students-per-page-select">
          <option v-for="option in studentsPerPageOptions" :key="option" :value="option">
            {{ option }}
          </option>
        </select>
      </div>
    </div>

    <table v-if="paginatedStudents.length" class="student-table">
      <thead>
        <tr>
          <th>Student ID</th>
          <th>Name</th>
          <th>Age</th>
          <th>Date of Birth</th>
          <th>Email</th>
          <th>Actions</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="student in paginatedStudents" :key="student.id">
          <td>{{ student.studentId }}</td>
          <td>{{ student.name }}</td>
          <td>{{ student.age }}</td>
          <td>{{ student.dob || 'N/A' }}</td>
          <td>{{ student.email }}</td>
          <td class="actions">
            <button @click="navigateTo(`/view/${student.id}`)" class="btn view-btn">
              <i class="fas fa-eye"></i>
            </button>
            <button @click="navigateTo(`/edit/${student.id}`)" class="btn edit-btn">
              <i class="fas fa-edit"></i>
            </button>
            <button @click="deleteStudent(student.id)" class="btn delete-btn">
              <i class="fas fa-trash"></i>
            </button>
          </td>
        </tr>
      </tbody>
    </table>

    <p v-else class="no-results">No students found.</p>

    <div v-if="filteredStudents.length > 0" class="bottom-controls">
      <p class="entries-info">
        Showing {{ startEntry }} to {{ endEntry }} of {{ filteredStudents.length }} entries
      </p>
      <div class="pagination-controls">
        <button 
          @click="changePage(currentPage - 1)" 
          :disabled="currentPage <= 1" 
          class="btn pagination-btn"
        >
          Previous
        </button>
        <span>Page {{ currentPage }} of {{ totalPages }}</span>
        <button 
          @click="changePage(currentPage + 1)" 
          :disabled="currentPage >= totalPages" 
          class="btn pagination-btn"
        >
          Next
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

const API_BASE = 'http://localhost:8080/students';

export default {
  data() {
    return {
      students: [],
      searchQuery: '',
      currentPage: 1,
      studentsPerPage: 5,
      studentsPerPageOptions: [5, 10, 15, 20],
    };
  },
  computed: {
    filteredStudents() {
      const query = this.searchQuery.toLowerCase();
      return this.students.filter(student =>
        student.name.toLowerCase().includes(query) ||
        student.studentId.toLowerCase().includes(query) ||
        student.email.toLowerCase().includes(query)
      );
    },
    totalPages() {
      return Math.ceil(this.filteredStudents.length / this.studentsPerPage) || 1;
    },
    paginatedStudents() {
      const start = (this.currentPage - 1) * this.studentsPerPage;
      return this.filteredStudents.slice(start, start + this.studentsPerPage);
    },
    startEntry() {
      return this.filteredStudents.length === 0 ? 0 : (this.currentPage - 1) * this.studentsPerPage + 1;
    },
    endEntry() {
      return Math.min(this.currentPage * this.studentsPerPage, this.filteredStudents.length);
    }
  },
  watch: {
    studentsPerPage() {
      this.currentPage = 1;
    },
    searchQuery() {
      this.currentPage = 1;
    },
    filteredStudents() {
      if (this.currentPage > this.totalPages) {
        this.currentPage = this.totalPages;
      }
    }
  },
  methods: {
    async fetchStudents() {
      try {
        const { data } = await axios.get(API_BASE);
        this.students = data;
      } catch (error) {
        console.error('Error fetching students:', error.message || error);
      }
    },
    navigateTo(path) {
      this.$router.push(path);
    },
    async deleteStudent(id) {
      try {
        await axios.delete(`${API_BASE}/${id}`);
        await this.fetchStudents();
      } catch (error) {
        console.error(`Error deleting student ID ${id}:`, error.message || error);
      }
    },
    changePage(page) {
      if (page >= 1 && page <= this.totalPages) {
        this.currentPage = page;
      }
    }
  },
  mounted() {
    this.fetchStudents();
  }
};
</script>

<style scoped>
/* Your styles remain unchanged, already well-structured */
.container {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  max-width: 1000px;
  margin: auto;
  padding: 20px;
  background: #f9f9f9;
  border-radius: 10px;
  box-shadow: 0 4px 10px rgba(0, 0, 0, 0.1);
}

.header-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  gap: 10px;
  margin-bottom: 20px;
}

.header {
  font-size: 2rem;
  color: #333;
}

.controls-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  gap: 15px;
  margin-bottom: 20px;
}

.search-input {
  min-width: 200px;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
  font-size: 1rem;
}

.students-per-page {
  display: flex;
  align-items: center;
  gap: 10px;
  flex-wrap: wrap;
}

.students-per-page-select {
  padding: 8px;
  font-size: 1rem;
  border-radius: 5px;
  border: 1px solid #ccc;
}

.student-table {
  width: 100%;
  border-collapse: collapse;
  margin-top: 20px;
  font-size: 1rem;
}

.student-table th,
.student-table td {
  padding: 12px;
  border: 1px solid #ccc;
  text-align: left;
}

.student-table th {
  background: #f4f4f4;
  font-weight: bold;
}

.student-table td {
  background: #fff;
}

.student-table tr:hover td {
  background: #f2f2f2;
}

.actions {
  display: flex;
  flex-wrap: wrap;
  gap: 8px;
  justify-content: stretch;
}

.actions .btn {
  flex: 1 1 auto;
  min-width: 0;
  text-align: center;
}

.no-results {
  text-align: center;
  font-size: 1rem;
  color: #777;
  margin-top: 30px;
}

.bottom-controls {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
  align-items: center;
  margin-top: 20px;
  gap: 15px;
}

.entries-info {
  font-size: 1rem;
  color: #555;
}

.pagination-controls {
  display: flex;
  align-items: center;
  gap: 10px;
}

.pagination-btn {
  padding: 8px 16px;
  background-color: #2196f3;
  color: white;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  border: none;
}

.pagination-btn:disabled {
  background-color: #ccc;
  cursor: not-allowed;
}

.btn {
  padding: 8px 16px;
  font-size: 1rem;
  border-radius: 5px;
  cursor: pointer;
  border: none;
}

.primary-btn {
  background-color: #4caf50;
  color: white;
  font-weight: bold;
}

.view-btn {
  background-color: #2196f3;
  color: white;
}

.edit-btn {
  background-color: #ff9800;
  color: white;
}

.delete-btn {
  background-color: #f44336;
  color: white;
}

@media (max-width: 768px) {
  .header-container,
  .controls-container,
  .bottom-controls {
    flex-direction: column;
    align-items: stretch;
  }

  .student-table {
    font-size: 0.9rem;
  }

  .entries-info,
  .pagination-controls {
    text-align: center;
  }
}

@media (max-width: 480px) {
  .actions {
    flex-direction: column;
  }

  .student-table th,
  .student-table td {
    padding: 8px;
  }

  .btn {
    width: 100%;
    text-align: center;
  }
}
</style>
