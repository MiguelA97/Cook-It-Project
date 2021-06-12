<template>
    <header>
        <nav>
            <h1><router-link to="/">Cook It</router-link></h1>
            <ul>
                <li><router-link to="/profile/:username/recipesLists">My Recipes</router-link></li>
                <li><router-link to="/recipe/addRecipe">Add Recipe</router-link></li> <!-- estes 3 so aparecem caso o user esteja logged in! -->
                <li><router-link to="/profile/:username/details">Account Info</router-link></li>
                <li><router-link to="/login">Login/Logout</router-link></li>   <!-- se estiver logged in, queremos fazer logout. caso contrário ir para pagina de login -->
            </ul>
        </nav>
    </header>
</template>

<style scoped>
header {
  width: 100%;
  height: 5rem;
  background-color: #1e7433;
  display: flex;
  justify-content: center;
  align-items: center;
}

header a {
  text-decoration: none;
  color: #fffafa;
  display: inline-block;
  padding: 0.75rem 1.5rem;
  border: 1px solid transparent;
}

a:active,
a:hover,
a.router-link-active {
  border: 1px solid white;
}

h1 {
  margin: 0;
}

h1 a {
  color: white;
  margin: 0;
}

h1 a:hover,
h1 a:active,
h1 a.router-link-active {
  border-color: transparent;
}

header nav {
  width: 90%;
  margin: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

header ul {
  list-style: none;
  margin: 0;
  padding: 0;
  display: flex;
  justify-content: center;
  align-items: center;
}

li {
  margin: 0 0.5rem;
}
</style>