/** @type {import('next').NextConfig} */
const nextConfig = {
  reactStrictMode: false,
  swcMinify: true,
  output: 'standalone',
  images: {
    loader: 'akamai',
    path: '',
  },
};

module.exports = nextConfig;
