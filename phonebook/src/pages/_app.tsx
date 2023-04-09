import { AppProps } from 'next/app';
import Head from 'next/head';
import { PhonebookProvider } from '@/src/context/PhonebookContext';

export default function App(props: AppProps) {
  const { Component, pageProps } = props;

  return (
    <>
      <Head>
        <title>Page title</title>
        <meta name="viewport" content="minimum-scale=1, initial-scale=1, width=device-width" />
      </Head>
      <PhonebookProvider>
        <Component {...pageProps} />
      </PhonebookProvider>
    </>
  );
}