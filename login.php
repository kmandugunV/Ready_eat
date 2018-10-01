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

$userId = $_POST['u_id'];
$userPwd = $_POST['u_pw'];


$result = mysqli_query($con,"select userPwd from user where userId='$userId' and userPwd='$userPwd';");
$row = mysqli_fetch_array($result);

 $total_rows = $row[0];
 
  if($total_rows > 0){
    echo 'success';
  }
  else{
    echo 'failure';
  }
  

mysqli_close($con);
?>