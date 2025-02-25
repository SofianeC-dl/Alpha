<template>
  <div>
    <h2>Connexion</h2>
    <form @submit.prevent="login">
      <div>
        <label>Nom d'utilisateur:</label>
        <input type="text" v-model="username" />
      </div>
      <div>
        <label>Mot de passe:</label>
        <input type="password" v-model="password" />
      </div>
      <button type="submit">Se connecter</button>
    </form>
    <div v-if="errorMessage" style="color:red">{{ errorMessage }}</div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: '',
      errorMessage: ''
    };
  },
  methods: {
    login() {
      axios.post('http://localhost:9090/alpha-web/authenticate', {
        username: this.username,
        password: this.password
      })
        .then(response => {
          localStorage.setItem('token', response.data.jwt);
          this.$router.push('/admin');
        })
        .catch(error => {
          this.errorMessage = "Erreur lors de la connexion : " + error.response.data;
        });
    }
  }
}
</script>
