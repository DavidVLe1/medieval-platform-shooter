import { useState, useEffect, useContext } from "react";
import { useNavigate } from "react-router-dom";
import AuthContext from "./context/AuthContext";

export default function SignUp() {
    const { handleAuthentication, handleUserId,handleUserData }=useContext(AuthContext);
    const [signUpFormData, setSignUpFormData] = useState({
        userId: 0,
        firstName: "",
        lastName: "",
        username: "",
        email: "",
        password: "",
        favoriteColor: "",
        gender: ""
    });
    const navigate = useNavigate();
    const handleChange = (event) => {
        const { name, value } = event.target;
        setSignUpFormData({ ...signUpFormData, [name]: value });
    };


    // useEffect(() => {
    //   console.log("Updated Form Data with userID:", signUpFormData);
    // }, [signUpFormData]);

    const handleSubmit = async (event) => {
        event.preventDefault();
        // console.log("Form Data:", signUpFormData);

        try {
            const response = await fetch("http://localhost:8080/api/user", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(signUpFormData),
            });

            if (response.ok) {
                const responseData = await response.json();
                console.log("responseData : "+responseData);
                const { userId } = responseData; // Extract userId from the response
                handleAuthentication(true);
                handleUserId(userId);
                handleUserData(responseData);
                // console.log("isUserId: "+isUserId); 1 means not guest


                const playerCharacterData = {//default generated.
                    playerChracterId: 0,
                    userId: userId,
                    timePlayedInSeconds: 0,
                    charactersLevel: 1,
                    maxHealth: 100,
                    health: 100,
                    damage: 10,
                    speed: 8,
                    healingPotions: 5,
                };

                try {
                    // Second fetch request for creating a player character
                    const playerResponse = await fetch("http://localhost:8080/api/playerCharacter", {
                        method: "POST",
                        headers: {
                            "Content-Type": "application/json",
                        },
                        body: JSON.stringify(playerCharacterData),
                    });

                    if (playerResponse.ok) {
                        console.log("Player Generated Successful");




                        // Extract the new player character id from the response
                        const playerCharacterResponseData = await playerResponse.json();
                        console.log("playerCharacterResponseData is: "+playerCharacterResponseData.playerCharacterId);
                        const newPlayerCharacterId = playerCharacterResponseData.playerCharacterId;


                        // If creating the player character is successful, create game events
                        const gameEventsData = {
                            // other game events data
                            gameEventsId: 0,
                            playerCharacterId: newPlayerCharacterId,
                            bossesKilled: 0,
                            legendaryItemObtained: false,
                            gameCompleted: false,
                        };
                        // fourth fetch request for creating gameEvents
                        try {
                            const gameEventsResponse = await fetch("http://localhost:8080/api/gameEvents", {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json",
                                },
                                body: JSON.stringify(gameEventsData),
                            });

                            if (gameEventsResponse.ok) {
                                console.log("Game Events Created Successfully");
                            } else {
                                const gameEventsErrorData = await gameEventsResponse.json();
                                console.error("Game Events Creation failed:", gameEventsErrorData);
                            }
                        } catch (gameEventsError) {
                            console.error("An error occurred while creating game events:", gameEventsError);
                        }


                        // Create world stats in a similar way
                        const worldStatsData = {
                            // other world stats data
                            worldStatsId: 0,
                            playerCharacterId: newPlayerCharacterId,
                            enemiesKilled:0,
                            itemsUsed:0,
                            timesDied:0,
                        };
                        // fourth fetch request for creating worldStats
                        try {
                            const worldStatsResponse = await fetch("http://localhost:8080/api/worldStats", {
                                method: "POST",
                                headers: {
                                    "Content-Type": "application/json",
                                },
                                body: JSON.stringify(worldStatsData),
                            });

                            if (worldStatsResponse.ok) {
                                console.log("World Stats Created Successfully");
                            } else {
                                const worldStatsErrorData = await worldStatsResponse.json();
                                console.error("World Stats Creation failed:", worldStatsErrorData);
                            }
                        } catch (worldStatsError) {
                            console.error("An error occurred while creating world stats:", worldStatsError);
                        }



                    } else {
                        const playerErrorData = await playerResponse.json();
                        console.error("Player Generated failed:", playerErrorData);
                    }
                } catch (playerError) {
                    console.error("An error occurred while creating a player character:", playerError);
                }

                console.log("Registration Successful");
                setSignUpFormData({ ...signUpFormData, userId });
                navigate("/")
            } else {
                const errorData = await response.json();
                console.error("Registration failed:", errorData);
            }
        } catch (error) {
            console.error("An error occurred:", error);
        }
    };


    const containerStyle = {
        backgroundColor: "rgba(255, 255, 255, 0.9)",
        padding: "10px",
        borderRadius: "20px",
        boxShadow: "0px 0px 10px rgba(0, 0, 0, 0.2)",
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        width: "80%",
        maxWidth: "600px",
        margin: "auto",
        minHeight: "50vh",
        marginTop: "20vh"
    };

    const labelStyle = {
        display: "block",
        marginBottom: "5px",
        fontWeight: "bold",
    };

    const inputStyle = {
        width: "100%",
        padding: "10px",
        marginBottom: "15px",
        borderRadius: "5px",
        border: "1px solid #ccc",
    };

    const buttonStyle = {
        backgroundColor: "#007bff",
        color: "#fff",
        padding: "10px 20px",
        borderRadius: "5px",
        border: "none",
        cursor: "pointer",
    };

    return (
        <div className="sign-up-page-background">
            <div style={containerStyle}>
                <h2 style={{ textAlign: "center", fontFamily: "'Press Start 2P', sans-serif" }}>Sign Up</h2>
                <form onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col-md-6 mb-3" style={{ marginBottom: "15px" }}>
                            <label htmlFor="firstName" className="form-label" style={labelStyle}>
                                First Name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="firstName"
                                name="firstName"
                                value={signUpFormData.firstName}
                                onChange={handleChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                        <div className="col-md-6 mb-3" style={{ marginBottom: "15px" }}>
                            <label htmlFor="lastName" className="form-label" style={labelStyle}>
                                Last Name
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="lastName"
                                name="lastName"
                                value={signUpFormData.lastName}
                                onChange={handleChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                    </div>
                    <div className="row">
                        <div className="col-md-6 mb-3" style={{ marginBottom: "15px" }}>
                            <label htmlFor="gender" className="form-label" style={labelStyle}>
                                Gender
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="gender"
                                name="gender"
                                value={signUpFormData.gender}
                                onChange={handleChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                        <div className="col-md-6 mb-3" style={{ marginBottom: "15px" }}>
                            <label htmlFor="favoriteColor" className="form-label" style={labelStyle}>
                                Favorite Color
                            </label>
                            <input
                                type="text"
                                className="form-control"
                                id="favoriteColor"
                                name="favoriteColor"
                                value={signUpFormData.favoriteColor}
                                onChange={handleChange}
                                required
                                style={inputStyle}
                            />
                        </div>
                    </div>
                    <div className="mb-3" style={{ marginBottom: "15px" }}>
                        <label htmlFor="username" className="form-label" style={labelStyle}>
                            Username
                        </label>
                        <input
                            type="text"
                            className="form-control"
                            id="username"
                            name="username"
                            value={signUpFormData.username}
                            onChange={handleChange}
                            required
                            style={inputStyle}
                        />
                    </div>
                    <div className="mb-3" style={{ marginBottom: "15px" }}>
                        <label htmlFor="email" className="form-label" style={labelStyle}>
                            Email
                        </label>
                        <input
                            type="email"
                            className="form-control"
                            id="email"
                            name="email"
                            value={signUpFormData.email}
                            onChange={handleChange}
                            required
                            style={inputStyle}
                        />
                    </div>
                    <div className="mb-3" style={{ marginBottom: "15px" }}>
                        <label htmlFor="password" className="form-label" style={labelStyle}>
                            Password
                        </label>
                        <input
                            type="password"
                            className="form-control"
                            id="password"
                            name="password"
                            value={signUpFormData.password}
                            onChange={handleChange}
                            required
                            style={inputStyle}
                        />
                    </div>
                    <button type="submit" className="btn btn-primary" style={buttonStyle}>
                        Sign Up
                    </button>
                </form>
            </div>
        </div>
    );
}
