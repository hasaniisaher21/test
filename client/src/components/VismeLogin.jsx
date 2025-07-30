// src/components/VismeLogin.jsx
// This code has been updated to match the new embed snippet from Visme.

import { useEffect } from 'react';

const VismeLogin = () => {
  // This useEffect hook still handles loading the external Visme script.
  // This part of the logic does not need to change.
  useEffect(() => {
    const script = document.createElement('script');
    script.src = 'https://static-bundles.visme.co/forms/vismeforms-embed.js';
    script.async = true;
    document.body.appendChild(script);

    // Cleanup function to remove the script when the component is no longer on the page.
    return () => {
      document.body.removeChild(script);
    };
  }, []); // Empty array ensures this runs only once when the component mounts.

  return (
    // The attributes of this div have been updated to match your new code.
    // - data-full-page is now "false"
    // - data-min-height is now "500px"
    // - The URL no longer has the "?fullPage=true" parameter
    <div
      className="visme_d"
      data-title="Sample Custom Form"
      data-url="g0ok9e08-sample-custom-form"
      data-domain="forms"
      data-full-page="false"
      data-min-height="500px"
      data-form-id="138965"
    />
  );
};

export default VismeLogin;
