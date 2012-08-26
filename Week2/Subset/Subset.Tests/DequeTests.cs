using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using NUnit.Framework;

namespace Subset.Tests
{
    [TestFixture]
    public class DequeTests
    {
        [Test]
        public void Given_null_item_add_should_throw_NullPointerException()
        {
            // arrange
            var deque = new Deque<string>();
            // act            
            // assert
            Assert.Throws<NullReferenceException>(() => deque.AddFirst(null));
            Assert.Throws<NullReferenceException>(() => deque.AddLast(null));
        }

        [Test]
        public void Should_return_isEmpty_after_ctor()
        {
            // arrange
            var deque = new Deque<string>();
            // act
            // assert
            Assert.IsTrue(deque.IsEmpty());
        }

        [Test]
        public void Add_element_should_increment_size()
        {
            // arrange
            var deque = new Deque<string>();
            // act
            deque.AddFirst("Item");
            deque.AddLast("Item2");
            // assert
            Assert.AreEqual(2, deque.Size());
        }

        [Test]
        public void Remove_element_should_decrease_size()
        {
            // arrange
            var deque = new Deque<string>();
            deque.AddFirst("Item");
            deque.AddFirst("Item2");
            // act
            deque.RemoveFirst();
            deque.RemoveLast();
            // assert
            Assert.AreEqual(0, deque.Size());
        }

        [Test]
        public void Remove_on_empty_queue_should_throw_NoSuchElementException()
        {
            // arrange
            var deque = new Deque<string>();
            // act            
            // assert
            Assert.Throws<NoSuchElementException>(() => deque.RemoveFirst());
            Assert.Throws<NoSuchElementException>(() => deque.RemoveLast());
        }

        [Test]
        public void Remove_item_should_return_last_added_item()
        {
            // arrange
            var deque = new Deque<string>();            
            deque.AddLast("LastItem");
            deque.AddFirst("Item");
            // act
            var actual = deque.RemoveFirst();
            var actualLast = deque.RemoveLast();
            // assert
            Assert.AreEqual("Item", actual);
            Assert.AreEqual("LastItem", actualLast);
        }
    }
}
