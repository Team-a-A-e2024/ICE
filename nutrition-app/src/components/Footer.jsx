function Footer() {
    const style = {
        textAlign: "center"
    }

    return(
        <footer style={style}>
            <p>Computer Science 1. Term ICE Project</p>
            <p>&copy; {new Date().getFullYear()} Cphbusiness</p>            
        </footer>
    );
}

export default Footer