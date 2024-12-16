import profilePic from '../assets/155369142.png'

function Card(){
    const style = {
        border: "1px solid hsl(0, 0%, 20%)",
        borderRadius: "10px",
        boxShadow: "5px 5px 5px hsla(0, 0%, 0%, 0.2)",
        padding: "20px",
        margin: "10px",
        textAlign: "center",
        maxWidth: "250px",
        display: "inline-block"
    }

    return(
        <div style={style} className="card">
            <img src={profilePic} width="100" alt="Image description"></img>
            <h2>Thomas</h2>
            <p>Computer Science Student</p>
        </div>
    );
}

export default Card