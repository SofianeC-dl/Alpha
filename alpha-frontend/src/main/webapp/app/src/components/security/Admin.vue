<template>
  <BaseLayout />
</template>
<script>
import axios from 'axios';
import BaseLayout from "@/components/sructure/BaseLayout.vue";

export default {
  name: 'Admin',
  components: {BaseLayout},
  data() {
    return {
      message: ''
    };
  },
  mounted() {
    const token = localStorage.getItem('token');
    axios.get('http://localhost:9090/alpha-web/admin/welcome', {
      headers: {
        'Authorization': 'Bearer ' + token
      }
    })
      .then(response => {
        this.message = response.data;
      })
      .catch(error => {
        this.message = 'Erreur : ' + error.response.data;
      });
  }
}
</script>
