/**
 *
 */

class GmoMember extends React.Component{
  constructor(props){
    super(props);
  }

  render(){
    return(
      <p>
      </p>
    )
  }
}

function getGmoMemberInfo(){
	var prm = {
			MemberId : $("#gmo_id").val()
	}
	// 1.$.ajaxメソッドで通信を行います。
	//  dataでは、フォームの内容をserialize()している
	//  →serialize()の内容は、cs1=custom1&cs2=custom2
	$.ajax({
		url:'/GetGmoMemberInfo', // 通信先のURL
		type:'GET',		         // 使用するHTTPメソッド (GET/ POST)
		data:prm,                // 送信するデータ
		dataType:'json',         // 応答のデータの種類
						         // (xml/html/script/json/jsonp/text)
		timeout:1000, 		     // 通信のタイムアウトの設定(ミリ秒)

		// 2. doneは、通信に成功した時に実行される
		//  引数のdata1は、通信で取得したデータ
		//  引数のtextStatusは、通信結果のステータス
		//  引数のjqXHRは、XMLHttpRequestオブジェクト
		}).done(function(data1,textStatus,jqXHR) {
				console.log(jqXHR.status); //jqXHR.statusを表示
				console.log(textStatus); //textStatusを表示

				// 4. オブジェクトをJSON形式の文字列に変換
				var data2 = JSON.stringify(data1);
				console.log(data2); //コンソールにJSON形式で表示される

				// 5.JSON形式の文字列をオブジェクトにし、
				// キーを指定して値(httpbin.org)を表示
				var data3 = JSON.parse(data2);

				if(data3.status == 0){
					ReactDOM.render(
							<div><p>会員ID：{data3.memberId}</p><p>会員名：{data3.memberName}</p></div>,
						    document.getElementById("member_info")
					);
					$("#out1").html('');
					$("#out2").html('');
					$("#out3").html('');
				}else{
					$("#out1").html('エラーが発生しました。');
					$("#out2").html(data3.error);
					$("#out3").html('');
				}
		// 6. failは、通信に失敗した時に実行される
		}).fail(function(jqXHR, textStatus, errorThrown ) {
				$("#out1").html(jqXHR.status); //jqXHR.statusを表示
				$("#out2").html(textStatus); //textStatusを表示
				$("#out3").html(errorThrown); //errorThrownを表示

		// 7. alwaysは、成功/失敗に関わらず実行される
		}).always(function(){;
	});
}
