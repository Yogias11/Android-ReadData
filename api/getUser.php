<?php

require_once 'koneksi.php';

header('Content-Type: application/json');

$query = "SELECT * FROM user";

$result = mysqli_query($conn, $query);

$array = array();

while ($row = mysqli_fetch_assoc($result))
{
	$array[] = $row;
}

echo ($result) ? json_encode(array('kode' => 1, 'result' => $array)):
                json_encode(array('kode' => 0, 'pesan' => 'Gagal menemukan data user'));
?>