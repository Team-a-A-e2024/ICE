import Header from '../components/Header'
import Footer from '../components/Footer'
import style from './HomePage.module.css'

function HomePage() {
  return (
    <>
      <Header></Header>
      <body>
        <p>
            <h3>NutriTrack: Your Personal Nutrition Assistant</h3>
            <h3>⭐⭐⭐⭐⭐</h3>
        </p>
        <p>
        Welcome to NutriTrack, the ultimate app designed to help you understand and manage your nutritional intake with ease and precision. 
        Whether you're a fitness enthusiast, someone with specific dietary needs, or just curious about your food's nutritional content, 
        NutriTrack is here to assist you.
        </p>
        <hr></hr>
        <p>
            <h3>Features</h3>
            <h3>⭐⭐⭐⭐⭐</h3>
        </p>
        <p>
            <ul>
                <li>
                <h4>Extensive Food Database 🍲</h4> 
                Browse through a vast list of food items, each with detailed nutritional information, including calories, proteins, fats, 
                carbohydrates, vitamins, and minerals. Our database is regularly updated to ensure accuracy and comprehensiveness.
                </li>
                <li>
                <h4>Customizable Meals 🥗</h4> 
                Easily add products to your dish and let NutriTrack calculate the total nutritional values for you. 
                Mixing different ingredients? No problem! NutriTrack will sum up all the nutritional values to give you a complete overview 
                of your meal's nutritional profile.
                </li>
                <li>
                <h4>Track Your Nutrition Goals 🎯</h4>
                Whether you’re aiming to gain muscle, lose weight, or simply maintain a balanced diet, NutriTrack helps you keep an eye 
                on your daily nutrient intake. Set your personal nutrition goals and track your progress with just a few taps.
                </li>
                <li>
                <h4>User-Friendly Interface 👆</h4>
                With its clean and intuitive interface, NutriTrack makes navigating through your nutritional data a breeze. Adding, editing, 
                and mixing ingredients into your dishes is straightforward, ensuring a seamless user experience.
                </li>
            </ul>
        </p>
        <hr></hr>
      </body>
      <Footer></Footer>
    </>
  );
}

export default HomePage