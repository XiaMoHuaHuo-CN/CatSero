<?php
$json = json_decode(curl("https://api.github.com/repos/XiaMoHuaHuo-CN/CatSero/releases"), true);
$name = $json[0]["name"];
$durl = $json[0]["assets"][0]["browser_download_url"];

echo json_encode(
    array(
        "name" => $name,
        "durl" => $durl
    )
);

function curl($url, $UA = 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/96.0.4664.93 Safari/537.36 Edg/96.0.1054.53', $refer = '', $timeout = 10) {
	$header[] = "Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    $header[] = "Accept-Encoding: gzip, deflate, sdch, br";
    $header[] = "Accept-Language: zh-CN,zh;q=0.9";
	$ch = curl_init();
	curl_setopt($ch, CURLOPT_URL, $url);
	curl_setopt($ch, CURLOPT_HTTPHEADER, $header);
	curl_setopt($ch, CURLOPT_USERAGENT, $UA);
	curl_setopt($ch, CURLOPT_REFERER, $refer);
	curl_setopt($ch, CURLOPT_RETURNTRANSFER, 1);
	curl_setopt($ch, CURLOPT_ENCODING, 'gzip,deflate');
	curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, FALSE);
	curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, FALSE);
	curl_setopt($ch, CURLOPT_TIMEOUT, $timeout);
	$output = curl_exec($ch);
	curl_close($ch);
	return $output;
}
?>