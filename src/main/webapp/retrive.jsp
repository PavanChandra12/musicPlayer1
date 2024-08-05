<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="javax.sql.rowset.serial.SerialBlob" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Music Player</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            padding-top: 20px;
        }

        .card {
            margin-bottom: 20px;
            border: 1px solid #ddd;
            border-radius: 8px;
            overflow: hidden;
        }

        .card-body {
            display: flex;
            flex-direction: column;
            align-items: center;
            padding: 15px;
        }

        .album-art {
            width: 100%;
            max-height: 200px;
            object-fit: cover;
            margin-bottom: 10px;
        }

        audio {
            width: 100%;
            margin-top: 10px;
        }

        /* Style the audio player controls */
        audio::-webkit-media-controls-panel {
            background-color: #f0f0f0;
            border-radius: 5px;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 5px;
        }

        audio::-webkit-media-controls-play-button,
        audio::-webkit-media-controls-pause-button {
            color: #333;
            background-color: transparent;
            border: none;
            font-size: 24px;
            cursor: pointer;
            outline: none;
        }

        audio::-webkit-media-controls-current-time-display,
        audio::-webkit-media-controls-time-remaining-display {
            color: #333;
            font-size: 14px;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="text-center">Music Player</h1>
        <div class="row justify-content-center">
            <%
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicplayer", "root", "root");

                    PreparedStatement stmt = conn.prepareStatement("SELECT * FROM music");
                    ResultSet rs = stmt.executeQuery();

                    while(rs.next()) {
                        Blob audioBlob = rs.getBlob("content");
                        byte[] audioBytes = audioBlob.getBytes(1, (int) audioBlob.length());
                        String base64Audio = new String(java.util.Base64.getEncoder().encode(audioBytes));

                        String imagePath = rs.getString("image_path");
                        %>
                        
                        <div class="col-md-4">
                            <div class="card">
                                <img class="card-img-top album-art" src="<%= imagePath %>" alt="Album Art">
                                <div class="card-body text-center">
                                    <h5 class="card-title"><%= rs.getString("name") %></h5>
                                    <audio controls>
                                        <source src='data:audio/mp3;base64,<%= base64Audio %>' type='audio/mp3' />
                                        Your browser does not support the audio element.
                                    </audio>
                                </div>
                            </div>
                        </div>
                        <%
                    }

                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
                }
            %>
        </div>
    </div>
</body>
</html>
