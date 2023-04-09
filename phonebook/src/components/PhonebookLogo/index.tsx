import React from 'react';
import Image from 'next/image';
import Logo from '@/src/assets/phonebook.svg';

const Phonebook = () => {
  return (
    <>
      <Image width={30} height={30}
        src={Logo} alt="test"/>
    </>
  );
};
export default Phonebook;