<?php

$servername = "localhost";
$username = "root";
$password = "password";
$dbname = "loginregister";

// Create connection
$conn = new mysqli($servername, $username, $password, $dbname);
// Check connection
if ($conn->connect_error) {
     die("Connection failed: " . $conn->connect_error);
} 

$id = $_POST["id"];

$query = "SELECT username, email FROM users WHERE id = '$id'";

$result = mysqli_query($conn, $query);  
$number_of_rows = mysqli_num_rows($result);

$response = array();

if($number_of_rows > 0) {
    while($row = mysqli_fetch_assoc($result)) {
        $response[] = $row;
    }
}

header('Content-Type: application/json');
echo json_encode(array("users"=>$response));
mysqli_close($conn);

?>