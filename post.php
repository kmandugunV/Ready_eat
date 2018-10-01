<?php

$host = "172.30.1.6";
$user = "root";
$password = "123456";
$db = "readyeat";

$con = mysqli_connect($host, $user, $password, $db);

mysqli_set_charset($con,"utf8");


if (mysqli_connect_errno($con))
{
   echo "Failed to connect to MySQL: " . mysqli_connect_error();
}

$userName = $_POST['Name'];
$userBirth = $_POST['Birth'];
$userId = $_POST['Id'];
$userPwd = $_POST['Pwd'];
$userPhone = $_POST['Phone'];


$result = mysqli_query($con,"insert into user values ('$userName', '$userBirth', '$userId', '$userPwd','$userPhone');");

  if($result){
    echo 'success';
  }
  else{
    echo 'Your ID is Already exist';
  }

mysqli_close($con);
?>