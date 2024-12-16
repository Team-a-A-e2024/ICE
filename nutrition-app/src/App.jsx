import { BrowserRouter as Router, Routes, Route, Navigate } from "react-router-dom";
import Home from './pages/HomePage'
import Products from './pages/ProductPage'
import Dishes from './pages/DishesPage'

function App() {
  return (
    <>
      <Router>
        <Routes>
          <Route exact path="/" element={<Home />} />
          <Route path="/products" element={<Products />}/>
          <Route path="/dishes" element={<Dishes />}/>
          <Route path="*" element={<Navigate to="/" />}/>
        </Routes>
      </Router>
    </>
  );
}

export default App
