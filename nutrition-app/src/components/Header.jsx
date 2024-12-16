import styles from './Header.module.css'

function Header() {
    return(
        <header>
            <h1>NutriTrack</h1>
            <nav>
                <ul>
                    <li><a href="/home">Home</a></li>
                    <li><a href="/products">Products</a></li>
                    <li><a href="/dishes">Dishes</a></li>
                </ul>
            </nav>
            <hr></hr>
        </header>
    );
}

export default Header