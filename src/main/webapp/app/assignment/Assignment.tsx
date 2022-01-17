import React, { useState } from 'react';
import './assignmentstyle.css';
import { Switch, useRouteMatch, useParams } from 'react-router-dom';

import Sidebar from './Sidebar';
import { Button, Form, Row } from 'react-bootstrap';
import { Axios } from 'axios';

const Assignment = () => {
  const { path } = useRouteMatch();
  const [selectedFile, setSelectedFile] = useState();
  const [stringVerify, setStringVerify] = useState();
  const [isFilePicked, setIsFilePicked] = useState(false);

  const changeHandler = event => {
    setSelectedFile(event.target.files[0]);
  };

  const changeHandler2 = event => {
    setStringVerify(event.target.text);
  };

  const handleSubmission = () => {
    const formData = new FormData();

    formData.append('file', selectedFile);

    fetch('/uploadFile', {
      method: 'POST',
      body: formData,
    })
      .then(response => response.json())
      .then(result => {
        console.log('Success:', result);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  const handleSubmissionString = () => {
    const formData = new FormData();

    formData.append('str', stringVerify);
    formData.append('username', '19IT132');

    fetch('/validate', {
      method: 'POST',
      body: formData,
    })
      .then(response => response.json())
      .then(result => {
        console.log('Success:', result);
      })
      .catch(error => {
        console.error('Error:', error);
      });
  };

  return (
    <div className="d-flex">
      <Sidebar />
      <div className="assignmentdetails">
        <div className="assigninternal">
          <h2>Assignment 1</h2>
          <h6>
            Instructor : Parth Shah <br />
            12/01/2022 <br />
            10 Points
          </h6>
          <hr />
          <p>
            Encryption and Decryption using SSL/TLS certificate : <br />
            <br />
            Install the openssl on your machine:
            <br />
            <br />
            For Windows:
            <br />
            <br />
            Download openssl for windows https://slproweb.com/products/Win32OpenSSL.html. Install the MSI.
            <br />
            <br />
            For MAC:
            <br />
            <br />
            brew update
            <br />
            brew install openssl
            <br />
            <span>echo &#39;export PATH=&#34;/usr/local/opt/openssl/bin:$PATH&#34;&#39; ~/.bash_profile</span>
            <br />
            source ~/.bash_profile
            <br />
            <br />
            Create SSL certificate using following command:
            <br />
            <br />
            openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:4096 -keyout private.key -out certificate.crt
            <br />
            <br />
            Extract the public key component from certificate using following command
            <br />
            <br />
            Command to extract pubkey from certificate
            <br />
            <br />
            openssl x509 -noout -pubkey -in certificate.crt -out pub.key
            <br />
            <br />
            Upload the public key to server “Upload Public Key” Button
            <br />
            <br />
            {/*<Button variant="primary">*/}
            {/*  <h6>Upload</h6>*/}
            {/*</Button>*/}
            {/*<br />*/}
            {/*<br />*/}
            {/*<Button variant="primary">*/}
            {/*  <h6>Submit</h6>*/}
            {/*</Button>*/}
            {/*<form method="POST" action="/uploadFile">*/}
            <input type="file" name="file" onChange={changeHandler} />
            <Button variant="primary" onClick={handleSubmission}>
              <h6>UPLOAD</h6>
            </Button>
            {/*</form>*/}
            {/*<Form action="">*/}
            {/*  <label htmlFor="myfile">Select a file:</label>*/}
            {/*  <input type="file" id="myfile" name="myfile"><br><br>*/}
            {/*    <input type="submit"/>*/}
            {/*</Form>*/}
            <br />
            <br />
            Server has generated the random no. in file and encrypted it with public key provided by you.
            <br /> Server has used following command to encrypt the file
            <br />
            <br />
            openssl rsautl -encrypt -in test.txt -pubin -inkey pub.key -out cipher.txt
            <br />
            <br />
            Encrypted file is sent to you through the mail. Download it and decrypt it using the following command
            <br />
            <br />
            openssl rsautl -decrypt -in cipher.txt -inkey private.key
            <br />
            <br />
            Paste the random no. in the following text box and validate the no.
            <br />
            <br />
            <input type="text" name="text" onChange={changeHandler2} />
            <Button variant="primary" onClick={handleSubmissionString}>
              <h6>Validate</h6>
            </Button>
            <br />
            <br />
            You have successfully completed the assignment.
            <br />
            <br />
          </p>
        </div>
      </div>
    </div>
  );
};

// const TestWhich = ({ txt }) => {
//   const [selectedFile, setSelectedFile] = useState(null);
//
//   // function submitButton(){
//   //   Axios.prototype.post("/uploadFile",selectedFile).then((response) =>{
//   //     console.log("Demo Demo")
//   //   })
//   // }
//   return (
//     <div className="assignmentdetails">
//       <div className="assigninternal">
//         <h2>Assignment 1</h2>
//         <h6>
//           Instructor : Parth Shah <br />
//           12/01/2022 <br />
//           10 Points
//         </h6>
//         <hr />
//         <p>
//           Encryption and Decryption using SSL/TLS certificate : <br />
//           <br />
//           Install the openssl on your machine:
//           <br />
//           <br />
//           For Windows:
//           <br />
//           <br />
//           Download openssl for windows https://slproweb.com/products/Win32OpenSSL.html. Install the MSI.
//           <br />
//           <br />
//           For MAC:
//           <br />
//           <br />
//           brew update
//           <br />
//           brew install openssl
//           <br />
//           <span>echo &#39;export PATH=&#34;/usr/local/opt/openssl/bin:$PATH&#34;&#39; ~/.bash_profile</span>
//           <br />
//           source ~/.bash_profile
//           <br />
//           <br />
//           Create SSL certificate using following command:
//           <br />
//           <br />
//           openssl req -x509 -sha256 -nodes -days 365 -newkey rsa:4096 -keyout private.key -out certificate.crt
//           <br />
//           <br />
//           Extract the public key component from certificate using following command
//           <br />
//           <br />
//           Command to extract pubkey from certificate
//           <br />
//           <br />
//           openssl x509 -noout -pubkey -in certificate.crt -out pub.key
//           <br />
//           <br />
//           Upload the public key to server “Upload Public Key” Button
//           <br />
//           <br />
//           {/*<Button variant="primary">*/}
//           {/*  <h6>Upload</h6>*/}
//           {/*</Button>*/}
//           {/*<br />*/}
//           {/*<br />*/}
//           {/*<Button variant="primary">*/}
//           {/*  <h6>Submit</h6>*/}
//           {/*</Button>*/}
//           {/*<form method="POST" action="/uploadFile">*/}
//           {/*  <input*/}
//           {/*    type="file"*/}
//           {/*    name="file"*/}
//           {/*    value={selectedFile}/>*/}
//           {/*  <Button variant="primary" type="submit"/>*/}
//           {/*</form>*/}
//
//           {/*<Form action="">*/}
//           {/*  <label htmlFor="myfile">Select a file:</label>*/}
//           {/*  <input type="file" id="myfile" name="myfile"><br><br>*/}
//           {/*    <input type="submit"/>*/}
//           {/*</Form>*/}
//           <br />
//           <br />
//           Server has generated the random no. in file and encrypted it with public key provided by you.
//           <br /> Server has used following command to encrypt the file
//           <br />
//           <br />
//           openssl rsautl -encrypt -in test.txt -pubin -inkey pub.key -out cipher.txt
//           <br />
//           <br />
//           Encrypted file is sent to you through the mail. Download it and decrypt it using the following command
//           <br />
//           <br />
//           openssl rsautl -decrypt -in cipher.txt -inkey private.key
//           <br />
//           <br />
//           Paste the random no. in the following text box and validate the no.
//           <br />
//           <br />
//           <Button variant="primary">
//             <h6>Validate</h6>
//           </Button>
//           <br />
//           <br />
//           You have successfully completed the assignment.
//           <br />
//           <br />
//         </p>
//       </div>
//     </div>
//   );
// };

export default Assignment;
