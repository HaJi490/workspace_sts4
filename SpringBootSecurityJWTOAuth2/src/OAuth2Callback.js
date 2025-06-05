import { UseEffect } from 'react';
import { UseNavigate } from 'react-router-dom';

const OAuth2Callback = ()=>{
	const navigate = UseNavigate();
	console.log('OAuth2CallBack');
	
	UseEffect(()=>{
		const fetchJWTToken = async()=>{
			fetch("http://localhost:8080/api/jwtcallback",{
				method:"POST",
				credentials:"include" //쿠키포함
			})
			.then((res)=>{
				console.log('res', res);
				const jwtToken = res.headers.get("Authorization");
				if(jwtToken){
					console.log('token saved!', jwtToken);
					sesseionStorage.setItem("jwtToken", jwtToken);
					navigate("/");
				}else{
					console.error("Authorization header is missing.");					
				}
			})
			.catch((error)=>{
				console.log("Error:", error);
			});
		}
		fetchJWTToken();
	}, [navigate]);
	return null;
};

export default OAuth2Callback;