import React, { useState } from 'react';

function App() {
  const [lunchPlace, setLunchPlace] = useState('');
  const [postcode, setPostcode] = useState('');
  const [submitRes, setSubmitRes] = useState('');
  const [getRes, setGetRes] = useState('');

  const handleInputChange = (e) => {
    if (e.target.name === 'lunchPlace') {
      setLunchPlace(e.target.value);
    } else {
      setPostcode(e.target.value);
    }
  };

  const handleSubmit = () => {
    if (!lunchPlace || !postcode) {
      alert('Place information incomplete');
    } else {
      // Send an HTTP POST request to the server
      fetch('http://localhost:8081/api/lunch-places', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({ "placeName": lunchPlace, "postcode": postcode }),
      })
        // .then((res) => res.json())
        .then((res) => {
          if (res.ok) {
            setSubmitRes('Lunch Place saved');
            console.log(res);
          } else {
            // return submitRes.text().then((error) => {
            res.json().then((data) => {
              setSubmitRes(data.message);
              console.log(data);
            });
            // });
          }
        })
        .catch((error) => {
          setSubmitRes(error.message);
        });
    }
  };

  const handleClear = () => {
    setLunchPlace('');
    setPostcode('');
    setSubmitRes('');
    setGetRes('');
  };

  const handleFindPlace = () => {
    // Send an HTTP GET request to the server
    fetch('http://localhost:8081/api/lunch-places/random')
      // .then((res) => res.json())
      .then((res) => {
        console.log(res);
        if (res.ok) {
          res.json().then((data) =>
            setGetRes(`Place Name: ${data.placeName}, Postcode: ${data.postcode}`));
        } else {
          res.json().then((data) => {
            setGetRes(data.message);
            console.log(data);
          });
        }
      })
      .catch((error) => {
        setGetRes(error.message);
      });
  };

  return (
    <div>
      <h1 style={{ fontWeight: 'bold' }}>Where to Eat Lunch</h1>
      <div>
        <input
          type="text"
          name="lunchPlace"
          placeholder="Lunch Place"
          value={lunchPlace}
          onChange={handleInputChange}
        />
        <input
          type="text"
          name="postcode"
          placeholder="Postcode"
          value={postcode}
          onChange={handleInputChange}
        />
        <button onClick={handleSubmit}>Submit</button>
        <button onClick={handleClear}>Clear</button>
      </div>
      <div style={{ color: 'green' }}>{submitRes}</div>
      <button onClick={handleFindPlace}>Find a Place</button>
      <div style={{ color: 'green' }}>{getRes}</div>
    </div>
  );
}

export default App;
